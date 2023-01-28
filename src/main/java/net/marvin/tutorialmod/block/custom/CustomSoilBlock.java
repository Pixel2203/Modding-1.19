package net.marvin.tutorialmod.block.custom;

import net.marvin.tutorialmod.Tutorialmod;
import net.minecraft.client.particle.Particle;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.HoeItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.common.IPlantable;

public class CustomSoilBlock extends Block {
    public static final BooleanProperty ISSOIL = BooleanProperty.create("soil");
    private static final int FERTILE_MAX = 5;
    public static final IntegerProperty FERTILE = IntegerProperty.create("fertile",0,FERTILE_MAX);


    public CustomSoilBlock(Properties p_49795_) {
        super(p_49795_);
        registerDefaultState(
         this.stateDefinition.any().setValue(ISSOIL,false).setValue(FERTILE,0)
        );
    }


    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(ISSOIL);
        builder.add(FERTILE);
    }

    @Override
    public void randomTick(BlockState blockState, ServerLevel level, BlockPos pos, RandomSource source) {
        Tutorialmod.LOGGER.info(Tutorialmod.LOGGER_PREFIX + " RandomTick!");
        BlockState plantState = level.getBlockState(pos.above());

        if(plantState.getBlock() instanceof IPlantable){
            Tutorialmod.LOGGER.info(Tutorialmod.LOGGER_PREFIX + " Pflanze auf diesem Block entdeckt!");
            Property<Integer> ageProperty = ((CropBlock)plantState.getBlock()).getAgeProperty();
            int plantAge = plantState.getValue(ageProperty);
            int maxAge = ((CropBlock) plantState.getBlock()).getMaxAge();
            if(plantAge < maxAge){
                if(blockState.getValue(FERTILE) > 0){
                    level.setBlock(pos,blockState.setValue(FERTILE,blockState.getValue(FERTILE)-1),3);
                    level.setBlock(pos.above(),plantState.setValue(ageProperty, plantAge+1),3);
                    level.addParticle(
                            ParticleTypes.PORTAL,
                            pos.getX(),
                            pos.getY(),
                            pos.getZ(),
                            0,
                            0,
                            0

                    );
                    Tutorialmod.LOGGER.info(Tutorialmod.LOGGER_PREFIX + " Particle Spawned at " + pos);

                }

            }



        }
    }

    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand interactionHand, BlockHitResult result) {
        if(interactionHand == InteractionHand.MAIN_HAND){
            boolean isSoil = state.getValue(ISSOIL);

            // Handling Hoe Behaviour
            ItemStack itemstack = player.getItemInHand(interactionHand);
            if(itemstack.getItem() instanceof HoeItem){
                if(!isSoil){
                    turnToSoil(state,level,pos,player);
                    return  InteractionResult.SUCCESS;
                }
                return InteractionResult.PASS;
            }
            // Handling Bone Meal Behaviour
            if(itemstack.getItem() == Items.BONE_MEAL && isSoil){
                return handleBonemealBehaviour(itemstack,pos,state,level);
            }

        }
        return super.use(state,level,pos,player,interactionHand,result);
    }
    private InteractionResult handleBonemealBehaviour(ItemStack itemStack, BlockPos pos, BlockState blockState, Level level){
        int fertility = blockState.getValue(FERTILE);
        if(fertility <FERTILE_MAX){
            fertility++;
            level.setBlock(pos,blockState.setValue(FERTILE,fertility),3);
            return InteractionResult.SUCCESS;
        }
        return InteractionResult.PASS;
    }

    @Override
    public boolean canSustainPlant(BlockState state, BlockGetter world, BlockPos pos, Direction facing, IPlantable plantable) {
        if(!state.getValue(ISSOIL)){
           return false;
        }
        Block plant = plantable.getPlant(world,pos).getBlock();
        if(plant == Blocks.CARROTS){
            return true;
        }
        return super.canSustainPlant(state, world, pos, facing, plantable);
    }


    private void turnToSoil(BlockState state, Level level, BlockPos pos, Player player){
        level.setBlock(pos,state.setValue(ISSOIL,true),3);
        player.playSound(SoundEvents.HOE_TILL);
    }
}

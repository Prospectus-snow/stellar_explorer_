package net.mcreator.stellarexplorer.procedures;

import net.minecraft.world.item.Items;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.event.entity.living.ShieldBlockEvent;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.sounds.SoundSource;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.BlockPos;

import net.mcreator.stellarexplorer.entity.AberrantMeteoriteCreeperEntity;

import javax.annotation.Nullable;
import java.util.Objects;

@Mod.EventBusSubscriber
public class ShieldBreakProcedure {

	@SubscribeEvent
	public static void whenEntityBlocksWithShield(ShieldBlockEvent event) {
		if (event == null) return;

		Entity entity = event.getEntity();
		Entity attacker = event.getDamageSource().getDirectEntity();

		if (entity instanceof Player player) {
			if (attacker instanceof AberrantMeteoriteCreeperEntity creeper
					&& creeper.getEntityData().get(AberrantMeteoriteCreeperEntity.DATA_InAttack)
					&& creeper.getEntityData().get(AberrantMeteoriteCreeperEntity.DATA_AttackType) == 2) {



				ItemStack shield = player.getUseItem();



				player.stopUsingItem();
				player.releaseUsingItem();



				if (shield.is(Items.SHIELD)) {
					player.getCooldowns().addCooldown(shield.getItem(), 100);

					shield.hurtAndBreak(1, player, p ->
							p.broadcastBreakEvent(player.getUsedItemHand())
					);
				}
				Level level = player.level();
				ResourceLocation soundId = new ResourceLocation("item.shield.break");
				if (!level.isClientSide()) {
					level.playSound(null, player.blockPosition(),
							Objects.requireNonNull(ForgeRegistries.SOUND_EVENTS.getValue(soundId)),
							SoundSource.PLAYERS, 1f, 1f);
				} else {
					level.playLocalSound(player.getX(), player.getY(), player.getZ(),
							Objects.requireNonNull(ForgeRegistries.SOUND_EVENTS.getValue(soundId)),
							SoundSource.PLAYERS, 1f, 1f, false);
				}
			}
		}
	}
}

package demo

import net.minecraft.block.material.Material
import net.minecraft.entity.item.EntityTNTPrimed
import net.minecraft.util.math.BlockPos
import net.minecraftforge.common.MinecraftForge.EVENT_BUS
import net.minecraftforge.event.world.ExplosionEvent
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent

@Mod(modid = "demo", modLanguage = "scala")
object DemoMod {

  EVENT_BUS.register(this)

  @SubscribeEvent def onExplosionStart(event: ExplosionEvent.Start): Unit = {
    val explosion = event.getExplosion
    val position = new BlockPos(explosion.getPosition)
    val stateBelow = event.getWorld.getBlockState(position.down)
    if(explosion.exploder.isInstanceOf[EntityTNTPrimed] && stateBelow.getMaterial == Material.ROCK)
      explosion.size *= 4
  }
}

package doublemoon.mahjongcraft.client.gui.icon

import com.mojang.authlib.GameProfile
import doublemoon.mahjongcraft.util.RenderHelper
import io.github.cottonmc.cotton.gui.widget.icon.Icon
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import net.fabricmc.api.EnvType
import net.fabricmc.api.Environment
import net.minecraft.block.entity.SkullBlockEntity
import net.minecraft.client.gui.DrawContext
import net.minecraft.client.util.math.MatrixStack
import java.util.*

class PlayerFaceIcon(
    val uuid: UUID,
    val name: String
) : Icon {
    private var gameProfile = GameProfile(uuid, name)

    init {
        loadGameProfileProperties()
    }

    private fun loadGameProfileProperties() {
        CoroutineScope(Dispatchers.IO).launch {
            SkullBlockEntity.loadProperties(gameProfile) { gameProfile = it }
        }
    }

    @Environment(EnvType.CLIENT)
    override fun paint(context: DrawContext, x: Int, y: Int, size: Int) {
        RenderHelper.renderPlayerFace(
            context = context,
            gameProfile = gameProfile,
            x = x,
            y = y,
            size = size
        )
    }
}
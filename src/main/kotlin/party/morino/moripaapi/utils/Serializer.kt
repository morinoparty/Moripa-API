package party.morino.moripaapi.utils

import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.json.Json
import org.bukkit.Bukkit
import org.bukkit.OfflinePlayer
import party.morino.moripaapi.utils.PlayerUtils.toUUID
import java.util.*


// UUID <==> String
object UUIDSerializer : KSerializer<UUID> {
    override val descriptor = PrimitiveSerialDescriptor("UUID", PrimitiveKind.STRING)

    override fun deserialize(decoder: Decoder): UUID {
        return decoder.decodeString().toUUID()
    }

    override fun serialize(encoder: Encoder, value: UUID) {
        encoder.encodeString(value.toString())
    }
}

// OfflinePlayer <==> UUID
object OfflinePlayerSerializer : KSerializer<OfflinePlayer> {
    override val descriptor = PrimitiveSerialDescriptor("OfflinePlayer", PrimitiveKind.STRING)

    override fun deserialize(decoder: Decoder): OfflinePlayer {
        return Bukkit.getOfflinePlayer(decoder.decodeString().toUUID())
    }

    override fun serialize(encoder: Encoder, value: OfflinePlayer) {
        encoder.encodeString(value.uniqueId.toString())
    }
}

val json = Json {
    encodeDefaults = true
    isLenient = true
    prettyPrint = true
}
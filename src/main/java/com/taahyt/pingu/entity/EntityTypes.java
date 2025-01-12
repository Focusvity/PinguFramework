package com.taahyt.pingu.entity;

import java.util.Arrays;

public enum EntityTypes {

    AREA_EFFECT_CLOUD(0),
    ARMOR_STAND(1),
    ARROW(2),
    AXOLOTL(3),
    BAT(4),
    BEE(5),
    BLAZE(6),
    BOAT(7),
    CAT(8),
    CAVE_SPIDER(9),
    CHEST_MINECART(51),
    CHICKEN(10),
    COD(11),
    COMMAND_BLOCK_MINECART(52),
    COW(12),
    CREEPER(13),
    DOLPHIN(14),
    DONKEY(15),
    DRAGON_FIREBALL(16),
    DROWNED(17),
    EGG(89),
    ELDER_GUARDIAN(18),
    END_CRYSTAL(19),
    ENDER_DRAGON(20),
    ENDER_PEARL(90),
    ENDERMAN(21),
    ENDERMITE(22),
    EVOKER(23),
    EVOKER_FANGS(24),
    EXPERIENCE_BOTTLE(91),
    EXPERIENCE_ORB(25),
    EYE_OF_ENDER(26),
    FALLING_BLOCK(27),
    FIREBALL(43),
    FIREWORK_ROCKET(28),
    FISHING_BOBBER(112),
    FOX(29),
    FURNACE_MINECART(53),
    GHAST(30),
    GIANT(31),
    GLOW_ITEM_FRAME(32),
    GLOW_SQUID(33),
    GOAT(34),
    GUARDIAN(35),
    HOGLIN(36),
    HOPPER_MINECART(54),
    HORSE(37),
    HUSK(38),
    ILLUSIONER(39),
    IRON_GOLEM(40),
    ITEM(41),
    ITEM_FRAME(42),
    LEASH_KNOT(44),
    LIGHTNING_BOLT(45),
    LLAMA(46),
    LLAMA_SPIT(47),
    MAGMA_CUBE(48),
    MARKER(49),
    MINECART(50),
    MOOSHROOM(58),
    MULE(57),
    OCELOT(59),
    PAINTING(60),
    PANDA(61),
    PARROT(62),
    PHANTOM(63),
    PIG(64),
    PIGLIN(65),
    PIGLIN_BRUTE(66),
    PILLAGER(67),
    PLAYER(111),
    POLAR_BEAR(68),
    POTION(92),
    PUFFERFISH(70),
    RABBIT(71),
    RAVAGER(72),
    SALMON(73),
    SHEEP(74),
    SHULKER(75),
    SHULKER_BULLET(76),
    SILVERFISH(77),
    SKELETON(78),
    SKELETON_HORSE(79),
    SLIME(80),
    SMALL_FIREBALL(81),
    SNOW_GOLEM(82),
    SNOWBALL(83),
    SPAWNER_MINECART(55),
    SPECTRAL_ARROW(84),
    SPIDER(85),
    SQUID(86),
    STRAY(87),
    STRIDER(88),
    TNT(69),
    TNT_MINECART(56),
    TRADER_LLAMA(94),
    TRIDENT(93),
    TROPICAL_FISH(95),
    TURTLE(96),
    VEX(97),
    VILLAGER(98),
    VINDICATOR(99),
    WANDERING_TRADER(100),
    WITCH(101),
    WITHER(102),
    WITHER_SKELETON(103),
    WITHER_SKULL(104),
    WOLF(105),
    ZOGLIN(106),
    ZOMBIE(107),
    ZOMBIE_HORSE(108),
    ZOMBIE_VILLAGER(109),
    ZOMBIFIED_PIGLIN(110);

    private final int id;

    EntityTypes(int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }

    public String getIdentifier() {
        return "minecraft:" + this.name().toLowerCase();
    }

    public static EntityTypes getByIdentifier(String identifier) {
        return Arrays.stream(values()).filter(mat -> mat.getIdentifier().equalsIgnoreCase(identifier)).findFirst().orElse(null);
    }

    public static EntityTypes getById(int id) {
        return Arrays.stream(values()).filter(mat -> mat.getId() == id).findFirst().orElse(null);
    }

}

package net.tadditions.mod.world.structures;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.gen.FlatGenerationSettings;
import net.minecraft.world.gen.feature.ProbabilityConfig;
import net.minecraft.world.gen.feature.StructureFeature;
import net.minecraft.world.gen.feature.structure.IStructurePieceType;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.settings.DimensionStructuresSettings;
import net.minecraft.world.gen.settings.StructureSeparationSettings;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.tadditions.mod.QolMod;
import net.tardis.mod.Tardis;
import net.tardis.mod.config.TConfig;
import net.tardis.mod.helper.Helper;
import net.tardis.mod.world.structures.MoonLanderStructure;
import net.tardis.mod.world.structures.MoonLanderStructurePieces;
import net.tardis.mod.world.structures.TStructures;

import java.util.function.Supplier;

public class MStructures {
    public static class Structures {
        public static final DeferredRegister<Structure<?>> STRUCTURES = DeferredRegister.create(ForgeRegistries.STRUCTURE_FEATURES, QolMod.MOD_ID);

        public static final RegistryObject<Structure<ProbabilityConfig>> SCORCHEED = setupStructure("scorched", () -> (new SchorchedStructure(ProbabilityConfig.CODEC)));
        public static IStructurePieceType SCORCHEED_PIECE = registerStructurePiece(ScorchedStructurePieces.Piece::new, "scorched");
        public static final RegistryObject<Structure<ProbabilityConfig>> SCORCHEEDCOMMON = setupStructure("scorched_common", () -> (new CommonSchorchedStructure(ProbabilityConfig.CODEC)));
        public static IStructurePieceType SCHORCHEEDCOMMON_PIECE = registerStructurePiece(CommonScorchedStructurePieces.Piece::new, "scorched_common");
        public static final RegistryObject<Structure<ProbabilityConfig>> MOON_TEMPLE = setupStructure("moon_temple", () -> (new MoonTempleStructure(ProbabilityConfig.CODEC)));
        public static IStructurePieceType MOON_TEMPLE_PIECE = registerStructurePiece(MoonTemplePieces.Piece::new, "moon_temple");
        public static final RegistryObject<Structure<ProbabilityConfig>> MARS_TEMPLE = setupStructure("mars_temple", () -> (new MarsTempleStructure(ProbabilityConfig.CODEC)));
        public static IStructurePieceType MARS_TEMPLE_PIECE = registerStructurePiece(MarsTemplePieces.Piece::new, "mars_temple");
        public static final RegistryObject<Structure<ProbabilityConfig>> VERGERUINS = setupStructure("verge_ruins", () -> (new VergeRuinsStructure(ProbabilityConfig.CODEC)));
        public static IStructurePieceType VERGERUINS_PIECE = registerStructurePiece(VergeRuinsPieces.Piece::new, "verge_ruins");
        public static final RegistryObject<Structure<ProbabilityConfig>> VERGESCRAPS = setupStructure("verge_scraps", () -> (new VergeScrapsStructure(ProbabilityConfig.CODEC)));
        public static IStructurePieceType VERGESCRAPS_PIECE = registerStructurePiece(VergeScrapsPieces.Piece::new, "verge_scraps");
        public static final RegistryObject<Structure<ProbabilityConfig>> VERGETORII = setupStructure("verge_torii", () -> (new VergeToriiStructure(ProbabilityConfig.CODEC)));
        public static IStructurePieceType VERGETORII_PIECE = registerStructurePiece(VergeToriiPieces.Piece::new, "verge_torii");
        public static final RegistryObject<Structure<ProbabilityConfig>> REMNANTS = setupStructure("remnants", () -> (new RemnantStructure(ProbabilityConfig.CODEC)));
        public static IStructurePieceType REMNANTS_PIECE = registerStructurePiece(RemnantPieces.Piece::new, "remnants");
    }
    public static void setupStructures() {
        TStructures.setupStructure(Structures.SCORCHEED.get(), new StructureSeparationSettings(5, 3, 46705573), true);
        TStructures.setupStructure(Structures.SCORCHEEDCOMMON.get(), new StructureSeparationSettings(1, 0, 76941716), true);
        TStructures.setupStructure(Structures.MOON_TEMPLE.get(), new StructureSeparationSettings(20, 15, 98164311), true);
        TStructures.setupStructure(Structures.MARS_TEMPLE.get(), new StructureSeparationSettings(20, 15, 35945413), true);
        TStructures.setupStructure(Structures.VERGERUINS.get(), new StructureSeparationSettings(20, 15, 25076368), false);
        TStructures.setupStructure(Structures.VERGESCRAPS.get(), new StructureSeparationSettings(20, 15, 44307830), true);
        TStructures.setupStructure(Structures.VERGETORII.get(), new StructureSeparationSettings(20, 15, 52085484), true);
        TStructures.setupStructure(Structures.REMNANTS.get(), new StructureSeparationSettings(20, 19, 61116830), false);
    }

    public static class ConfiguredStructures {

        public static StructureFeature<?, ?> CONFIGURED_SCORCHEED = Structures.SCORCHEED.get().withConfiguration(new ProbabilityConfig(1F));
        public static StructureFeature<?, ?> CONFIGURED_SCORCHEEDCOMMON = Structures.SCORCHEEDCOMMON.get().withConfiguration(new ProbabilityConfig(1F));
        public static StructureFeature<?, ?> CONFIGURED_MARSTEMPLE = Structures.MARS_TEMPLE.get().withConfiguration(new ProbabilityConfig(1F));
        public static StructureFeature<?, ?> CONFIGURED_MOONTEMPLE = Structures.MOON_TEMPLE.get().withConfiguration(new ProbabilityConfig(1F));
        public static StructureFeature<?, ?> CONFIGURED_VERGERUIN = Structures.VERGERUINS.get().withConfiguration(new ProbabilityConfig(1F));
        public static StructureFeature<?, ?> CONFIGURED_VERGETORII = Structures.VERGETORII.get().withConfiguration(new ProbabilityConfig(1F));
        public static StructureFeature<?, ?> CONFIGURED_VERGESCRAPS = Structures.VERGESCRAPS.get().withConfiguration(new ProbabilityConfig(1F));
        public static StructureFeature<?, ?> CONFIGURED_REMNANTS = Structures.REMNANTS.get().withConfiguration(new ProbabilityConfig(1F));



        public static void registerConfiguredStructures() {
            registerConfiguredStructure("scorched", Structures.SCORCHEED, CONFIGURED_SCORCHEED);
            registerConfiguredStructure("scorched_common", Structures.SCORCHEEDCOMMON, CONFIGURED_SCORCHEEDCOMMON);
            registerConfiguredStructure("mars_temple", Structures.MOON_TEMPLE, CONFIGURED_MARSTEMPLE);
            registerConfiguredStructure("moon_temple", Structures.MARS_TEMPLE, CONFIGURED_MOONTEMPLE);
            registerConfiguredStructure("verge_ruins", Structures.VERGERUINS, CONFIGURED_VERGERUIN);
            registerConfiguredStructure("verge_scraps", Structures.VERGESCRAPS, CONFIGURED_VERGESCRAPS);
            registerConfiguredStructure("verge_torii", Structures.VERGETORII, CONFIGURED_VERGETORII);
            registerConfiguredStructure("remnants", Structures.REMNANTS, CONFIGURED_REMNANTS);


        }

        private static <T extends Structure<?>> void registerConfiguredStructure(String registryName, Supplier<T> structure, StructureFeature<?, ?> configuredStructure) {
            Registry<StructureFeature<?, ?>> registry = WorldGenRegistries.CONFIGURED_STRUCTURE_FEATURE;
            Registry.register(registry, new ResourceLocation(QolMod.MOD_ID, registryName), configuredStructure);

            FlatGenerationSettings.STRUCTURES.put(structure.get(), configuredStructure);
        }
    }

    private static <T extends Structure<?>> RegistryObject<T> setupStructure(String name, Supplier<T> structure) {
        return Structures.STRUCTURES.register(name, structure);
    }

    public static IStructurePieceType registerStructurePiece(IStructurePieceType type, String key) {
        return Registry.register(Registry.STRUCTURE_PIECE, new ResourceLocation(QolMod.MOD_ID, key), type);
    }

}

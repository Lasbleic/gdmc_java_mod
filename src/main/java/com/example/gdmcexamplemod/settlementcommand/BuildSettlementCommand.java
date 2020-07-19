package com.example.gdmcexamplemod.settlementcommand;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.command.arguments.BlockPosArgument;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.server.ServerWorld;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class BuildSettlementCommand<S> {

    private static final String COMMAND_NAME = "buildsettlement";
    private static final Logger LOGGER = LogManager.getLogger();

    private BuildSettlementCommand() { }

    public static void register(CommandDispatcher<CommandSource> dispatcher) {
        dispatcher.register(Commands.literal(COMMAND_NAME)
                .then(Commands.argument("from", BlockPosArgument.blockPos()).then(Commands.argument("to", BlockPosArgument.blockPos()).executes( context -> {
                    return perform(context, BlockPosArgument.getLoadedBlockPos(context, "from"), BlockPosArgument.getLoadedBlockPos(context, "from"));
                }))));
    }

    private static int perform(CommandContext<CommandSource> commandSourceContext, BlockPos from, BlockPos to) {
        ServerWorld world = commandSourceContext.getSource().getServer().getWorld(DimensionType.OVERWORLD);

        LOGGER.info("BuildSettlement command has been performed");

        commandSourceContext.getSource().getEntity().sendMessage(new StringTextComponent("Hello World!"));
        commandSourceContext.getSource().getEntity().sendMessage(new StringTextComponent("You want to build a settlement from " + from.toString() + " to " + to.toString()));

        return 1;
    }
}

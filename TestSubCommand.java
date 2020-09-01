import some.package.SubCommandManager;
import java.util.Arrays;

@SubCommandManager.SubCommand(name = "test", description = "test subcommand", usage = "test")
public class TestSubCommand implements SubCommandManager.SubCommandExecutor {
    @Override
    public void execute(CommandSender sender, String[] args) {
        sender.sendMessage("This is a test command, your subcommand args are " + ChatColor.YELLOW + Arrays.toString(args));
    }
}

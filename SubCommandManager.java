import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.*;

public class SubCommandManager {

    private static Map<SubCommand, SubCommandExecutor> subCommands = new HashMap<>();

    public static void addSubCommand(SubCommandExecutor executor) {
        Class<? extends SubCommandExecutor> cls = executor.getClass();
        if (cls.isAnnotationPresent(SubCommand.class)) {
            subCommands.put(cls.getAnnotation(SubCommand.class), executor);
        } else {
            throw new IllegalArgumentException("Given executor has no SubCommand annotation.");
        }
    }

    public static SubCommandState execute(CommandSender sender, String[] commandArgs) {
        String commandName = commandArgs[0];
        Optional<SubCommand> subCommandOptional = subCommands.keySet().stream().filter(subCommand ->
                subCommand.name().equals(commandName)
                || Arrays.stream(subCommand.aliases()).map(String::toLowerCase).anyMatch(commandName::equals)).findFirst();
        if (!subCommandOptional.isPresent()) {
            return SubCommandState.NOT_FOUND;
        }

        SubCommand command = subCommandOptional.get();
        if (!command.permission().isEmpty() && !sender.hasPermission(command.permission())) {
            return SubCommandState.NO_PERMISSION;
        }

        String[] args = Arrays.copyOfRange(commandArgs, 1, commandArgs.length);
        if (command.argsLength() != -1 && command.argsLength() != args.length) {
            return SubCommandState.INVALID_ARG_LENGTH;
        }

        subCommands.get(command).execute(sender, args);
        return SubCommandState.EXECUTED;
    }

    public static Set<SubCommand> getSubCommands() {
        return subCommands.keySet();
    }

    public enum SubCommandState {
        NOT_FOUND,
        NO_PERMISSION,
        INVALID_ARG_LENGTH,
        EXECUTED
    }

    @Retention(RetentionPolicy.RUNTIME)
    public @interface SubCommand {
        String name();
        String description();
        String usage();
        String permission() default "";
        String[] aliases() default {};
        int argsLength() default -1;
    }

    public interface SubCommandExecutor {
        void execute(CommandSender sender, String[] args);
    }
}

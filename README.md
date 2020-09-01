# SubCommandManager
Easy to use Java Sub-Command Manager (created for bukkit, could use in any software by changing CommandSender)

## Usage

Create class named SubCommandManager.java and paste the content from https://github.com/luca-gg/SubCommandManager/blob/master/SubCommandManager.java.

In your command manager check if the arguments length is > 0, then pass arguments to your SubCommandManager and have fun with the returned state.

```java
if (args.length > 0) {
  var executionState = SubCommandManager.execute(commandSender, args);
  switch (executionState) {
    case NOT_FOUND:
      commandSender.sendMessage(SUBCOMMAND_NOT_FOUND);
      break;
    case NO_PERMISSION: 
      commandSender.sendMessage(SUBCOMMAND_INSUFFICIENT_PERMISSIONS);
      break;
    case INVALID_ARG_LENGTH: 
      commandSender.sendMessage(SUBCOMMAND_INVALID_ARG_LENGTH);
      break;
    default:
  }
}
```

(dont copy and paste this code if you are using java8 like the most spigot programmers, you need to change var to SubCommandManager.SubCommandState)

Example subcommand can be found at https://github.com/luca-gg/SubCommandManager/blob/master/TestSubCommand.java.

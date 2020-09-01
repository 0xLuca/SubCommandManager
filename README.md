# SubCommandManager
Easy to use Java Sub-Command Manager (created for bukkit, could use in any software by changing CommandSender)

## Usage

Create class named SubCommandManager.java and paste the contents from XXX.

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

Example subcommand can be found at XXX.

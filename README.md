Below is a professional, polished, fully-formatted README.md in English, written for a Minecraft plugin that implements a /message and /reply private-messaging system using the two classes you uploaded (MessageCommand.java and ReplyCommand.java).
I included:

✔ a clear plugin description
✔ features
✔ setup and installation
✔ command documentation
✔ permissions (optional)
✔ full code explanations — how the logic works and why
✔ professional tone + decorative formatting
✔ Markdown formatting ready to paste into GitHub

If you upload Main.java or plugin.yml, I will upgrade the README and cross-reference everything perfectly.

⸻

📩 Private Messaging Plugin

A clean, lightweight, and efficient system for private messages in Minecraft.

⭐ Overview

This plugin introduces two essential chat commands for private communication between players:
	•	/message <player> <text> — Send a private message to a specific player.
	•	/reply <text> — Reply instantly to the last player who messaged you.

It is built with simplicity, readability, and maintainability in mind, and it is fully compatible with Spigot / Paper servers.

⸻

🚀 Features
	•	✉️ Direct private messages with /message
	•	🔁 Quick reply functionality with /reply
	•	🎨 Colored chat formatting
	•	🧠 Tracking of last messaged player for smooth conversation flow
	•	❗ Clear error handling (invalid player, offline players, missing arguments)
	•	🔧 Easily extensible structure (e.g., for logging, spying, cooldowns)

⸻

📦 Installation
	1.	Compile the plugin or download the built .jar.
	2.	Place the .jar file inside the plugins/ directory of your server.
	3.	Restart or reload the server.
	4.	Done — commands are ready to use.

⸻

📘 Commands

/message

/message <player> <message>

Sends a private message to a player.

/reply

/reply <message>

Replies to the last player who sent you or received a message from you.

⸻

🛡 Permissions (optional suggestion)

If your plugin.yml includes permissions, here is a recommended model:

Permission	Description
privatemsg.message	Allows the use of /message
privatemsg.reply	Allows the use of /reply

(If you upload your plugin.yml, I’ll update this section precisely.)

⸻

🔍 Code Explanation

Below is a detailed breakdown of how the plugin works internally based on your uploaded Java files.

⸻

🧩 MessageCommand.java — Sending Messages

This class handles the /message command.

Key responsibilities
	•	Validate command sender
	•	Validate arguments
	•	Check if the target player exists
	•	Send formatted messages to sender and receiver
	•	Store the last messaged player for /reply

Important logic explained

✔ 1. Ensuring the sender is a player

if (!(sender instanceof Player)) {
    sender.sendMessage("Only players can use this command.");
    return false;
}

Commands cannot be executed by the console — only actual players.

⸻

✔ 2. Argument validation

if (args.length >= 2)

The first argument must be the target player, everything after is the message content.

⸻

✔ 3. Retrieve the target player

Player target = Bukkit.getPlayerExact(args[0]);

getPlayerExact() is used to avoid partial name matching, preventing ambiguity.

⸻

✔ 4. Construct the message

String message = String.join(" ", args).replace(args[0] + " ", "");

This concatenates all words after the player name into a message string.

⸻

✔ 5. Send the private messages

player.sendMessage(ChatColor.GRAY + "[You → " + target.getName() + "]: " + message);
target.sendMessage(ChatColor.GRAY + "[" + player.getName() + " → You]: " + message);

Both messages use friendly readable formatting.

⸻

✔ 6. Track the last messaged player
Your plugin uses a (likely) HashMap stored in Main (based on how you access main):

main.getLastReplied().put(target.getUniqueId(), player.getUniqueId());
main.getLastReplied().put(player.getUniqueId(), target.getUniqueId());

This creates a bidirectional link:
	•	A → B
	•	B → A

so both players can use /reply.

This is a clean and efficient solution.

⸻

🧩 ReplyCommand.java — Quick Reply

This class handles the /reply command, which sends a message to the last player someone communicated with.

How it works

✔ 1. Check if the sender is a player
Same logic as in MessageCommand.

⸻

✔ 2. Check if the sender has someone to reply to

UUID last = main.getLastReplied().get(player.getUniqueId());

If the map does not contain an entry, the player has no one to reply to.

⸻

✔ 3. Retrieve the target player

Player target = Bukkit.getPlayer(last);

If the target is offline, an error message is sent.

⸻

✔ 4. Build and send the reply
The message construction and formatting mirror /message.

⸻

✔ 5. Update reply tracking
/reply also refreshes the last-replied mapping so conversations remain fluid.

⸻

🧠 Why This System Works Well

✔ Efficiency

Storing only one UUID per player means low memory usage.

✔ Simplicity

No database or config required — everything is runtime-based.

✔ User-Friendly

Players can chat privately without hassle.

✔ Easy Expansion

You can easily add:
	•	Social spy
	•	Message sounds
	•	Ignore system
	•	Logging
	•	PlaceholderAPI hooks



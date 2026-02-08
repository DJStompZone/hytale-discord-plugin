<div align="center"> <h1>HyPhoenix</h1>

<img src="https://i.imgur.com/7q1XWa1.jpeg" width=512>
<br>
</div>

## Synopsis

**HyPhoenix** is a Hytale server plugin that bridges in-game chat with external systems via Python (Py4J), enabling real-time chat relays such as **Hytale ⇄ Discord**.

- All **Hytale-side logic** is done in Java
- All **external integrations** (Discord bots, moderation, etc.) are delegated to Python

## Features

- Listens for in-game player chat and forwards chat messages to Py4J gateway
- Receives messages from Python and broadcasts them back into Hytale chat

## Architecture Overview

```
Hytale Server
│
├─ HyPhoenix Plugin
│  ├─ PlayerChatEvent listener
│  ├─ Message formatting
│  ├─ Py4J Gateway
│
└── Py4J Bridge
    │
    └─ Python Process
       ├─ Discord bot (discord.py)
       └─ External logic / filtering
```

Loop prevention is handled at the application level.

## Project Structure

```
src/main/java/com/stompzone/relay/
├─ HyPhoenix.java
├─ bridge/
│  └─ Py4JHolder.java
├─ chat/
│  ├─ PlayerChatListener.java
│  └─ ChatBroadcaster.java
```

---

## Requirements

- Java 21+ (same as Hytale server)
- Gradle (wrapper included)
- Python 3.9+
- HytaleServer.jar available locally (Available through the Hytale website)

---

## Dependencies

### Java
- Hytale Server API
- Py4J (`net.sf.py4j:py4j`)

### Python
```bash
pip install py4j discord.py
```

## Building

```powershell
./gradlew.bat build
```

Produces a shaded ready-to-use JAR in `build/libs/`

## Installation

Place your JAR in your Hytale server's `mods/` directory and you're good to go.

## Loop Prevention

HyPhoenix does not enforce loop prevention automatically.

Recommended:
- Tag Discord-originated messages
- Ignore relay bot messages
- Maintain a short-lived message cache

## To-Do

- [x] Core relay implemented
- [x] Clean build
- [x] Plugin lifecycle correct
- [ ] Python Discord bridge
- [ ] Config support

## License

MIT License. See the [LICENSE](LICENSE) file for details.

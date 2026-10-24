# Notist

> 🚧 Toy project. Expect broken things.  
> Although I wrote lots of code, there might also be a few AI slops.

Notist is local-first, block-based note editor, built with Kotlin Multiplatform and Jetpack Compose.

## Quick Look

> (TODO: picture here), nothing implemented so far. everything is just plan

- Block based editor, with rich formatting and embeds.
- Local-first, but can be easily synchronized via self-hosted server or Git.
- Real-time collaboration over server or P2P.
- Deeply integrated, pluggable AI agents.
- Notes automated via inline JS script and plugins.
- Aims to be lightweight and quick. (but why you chose Kotlin / Jetpack Compose? IDK just preference)

---

## Key Features

- **Block-Based WYSIWYG Editor**:
    - List-like selection, drag handle, page as block, mention and reference.
    - Rich formatting, with opinionated styles; extensible via themes and plugins.
    - Tables, databases, interactive HTML document, programmed controls.
    - Macros and workflows, invoked by slash command or modification.

- **Local-First & Synchronization**:
    - Works like good old Git repo with .md files. Notes can be stored with extended Markdown format.
    - May also connect to server and work like online editor.

- **Real-Time Collaboration**:
    - Connects to server, remote P2P, or local LAN peers.
    - Encrypted end-to-end.
    - Currently uses old operation-based sync, but can be extended by plugins and planned to add CRDT support.
        * this is planned to be used by me. only me. why bother add collaboration feature. (unless required)

- **Extensible Plugin System**:
    - Embed script blocks to dynamically generate interactive content.
    - May use JS to create simple plugins.
    - Use Kotlin MPP for fully fledged plugins. These should be shipped compile-time. i.e. I'm planning to add
      'learning assistant' that connects to Canvas LMS.

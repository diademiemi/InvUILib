# InvUILib
InvUILib is a library for creating GUIs in Minecraft using the inventory system. It is designed to be simple to use and easy to understand.

## Installation
Plugins using InvUILib should include it as a dependency in their `plugin.yml` file. This can be done by adding the following lines to the `plugin.yml` file:

```yaml
depend: ["InvUiLib"]
```

InvUILib should also be installed on the server when a plugin using it is installed. This can be done by placing the `InvUiLib.jar` file in the `plugins` folder of the server.
The latest version of InvUILib can be downloaded from the [releases page](https://github.com/diademiemi/invuilib/releases).

## Usage
InvUILib provides a simple API for creating GUIs in Minecraft. The main class in the library is `Menu`, which represents a GUI menu. A `Menu` can contain multiple `MenuButton`s, which represent items in the menu.

A helper interface `Dialog` is provided which safely opens a menu for the player, first closing any existing menu they have open.

### Adding dependencies
To use InvUILib in your plugin, you should add the following dependency to your `build.gradle` or `pom.xml` file:

## Gradle

```gradle
dependencies {
    compileOnly "me.diademiemi:invuilib:0.1"
}
```

## Maven

```xml
<dependency>
    <groupId>me.diademiemi</groupId>
    <artifactId>invuilib</artifactId>
    <version>0.1</version>
</dependency>
```

This will require you to set the `GITHUB_ACTOR` and `GITHUB_TOKEN` environment variables to your GitHub username and a personal access token. This is sadly a limitation of the GitHub Maven Package Registry.
### Creating a Menu
To create a new `Menu`, you should implement the `Dialog` interface and override the `create` method. This method should return a new instance of the `Menu` class, with the desired title and size of the menu.

The returned `Menu` can be created with a `MenuBuilder` object, which allows you to add buttons to the menu.

Example:
```java
import me.diademiemi.invuilib.menu.*;
import org.bukkit.Material;
import org.bukkit.entity.Player;

public class DgExample implements Dialog {

    @Override
    public Menu create(Player p, Object... args) {

        MenuBuilder builder = new MenuBuilder("Example menu", MenuSize.THREE_ROWS, p);

        builder.addButtonByColumnRow(new MenuButton(Material.LIME_CONCRETE, 1, "Button") {
            @Override
            public void onLeftClick(Player p) {
                p.sendMessage("You clicked the button!");
            }

        }, 3, 1, true);

        builder.addButtonByColumnRow(new MenuButton(Material.BARRIER, 1, "Close") {
            @Override
            public void onLeftClick(Player p) {
                close(p);
            }
        }, 3, 2, true);

        builder.setOnOpen(() -> {
            p.sendMessage("Test menu opened!");
        });

        builder.setOnClose(() -> {
            p.sendMessage("Test menu closed!");
        });

        builder.setOnForceClose(() -> {
            p.sendMessage("Test menu force closed!");
        });

        return builder.build();
    }
}
```

In this example, a new `Menu` is created with the title "Example menu" and size `MenuSize.THREE_ROWS`. Two buttons are added to the menu, one with the text "Button" and one with the text "Close". The `onLeftClick` method of the buttons is overridden to send a message to the player when the button is clicked.
`addButtonByColumnRow` is used to add the buttons to the menu at specific coordinates. When the third argument is `true`, the first and last columns are omitted for Bedrock compatibility (Pocket edition uses 7 columns on inventories).
In future versions of InvUILib, I will provide more tools to build menus which can be optimised for Java and Bedrock without this kind of workaround.

This dialog can now be showed to a player by calling `new DgExample().open(p)`, where `p` is the player to show the menu to. 

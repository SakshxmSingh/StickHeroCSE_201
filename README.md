# Stick Hero Game 🎮
- Group 1: Saksham Singh and Sidhartha Garg
- CSE201 Advanced Programming at IIITD

This is a Java implementation of the Stick Hero game using JavaFX.

## Getting Started 🚀

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes.

### Prerequisites

- Java 11 or higher
- Maven
- JavaFX SDK
- Axis Bold font

### Installing

1. Navigate into the attached project `cd stickhero_cse201`
2. Use Maven to build the project `./mvnw clean install`

### Setting up JavaFX

To set up JavaFX in your IDE, follow these steps:

- Download the JavaFX SDK from [here](https://gluonhq.com/products/javafx/)
- Extract the downloaded file to a desired location
- In your IDE, add the `lib` directory of the extracted JavaFX SDK as a library to the project

### Installing the Font

The game uses the Axis Bold font. Make sure it's installed on your system.
`src/resources/com/example/stickhero_cse201/fonts/FontsFree-Net-AXIS-Extra-Bold-800.ttf`

## Running the game ▶️

To run the game, execute the `Main` class in your IDE. If you're using a terminal, you can use the following command:

```
./mvnw exec:java -Dexec.mainClass="com.example.stickhero_cse201.StickHeroMain"
```

## Game Functionality 🕹️

The game involves a stick figure that needs to cross platforms of varying widths. The player controls the length of the stick that the figure uses to cross the platforms. If the stick is too short or too long, the figure falls and the game is over.

The player can rotate the stick upside down and vice versa during its animation using the space key. Stick length controls are done by mouse press and release.

## Saving and Loading Game 💾

The game uses Java serialization to save and load game states. When you save a game, the current game state is serialized and written to a file. When you load a game, the game state is read from the file and deserialized.

## Built With

- [JavaFX](https://openjfx.io/) - The GUI framework used
- [Maven](https://maven.apache.org/) - Dependency Management

## Contributing and Authors ✨

- Saksham Singh (2022434) [GitHub](https://www.github.com/SakshxmSingh)
- Sidhartha Garg (2022499) [GitHub](https://www.github.com/GargSidhartha)

All the work was collectively and equally done by both authors.


# SameGame: Innocence

A Java implementation of the classic puzzle game SameGame, featuring a medieval theme inspired by the amazing Plague Tale games.

## Overview

SameGame is a puzzle game where the goal is to clear as many colored blocks from the board as possible. Blocks can only be removed when they form a group of two or more of the same color connected horizontally or vertically. The larger the group removed is, the higher is the score.

# Introduction

This project is a semester project. I had very strict requirements to follow but still free for the design and to add some features if I wanted to. Since at that time I was playing A Plague Tale: Innocence and A Plague Tale: Requiem, I decided to use the medieval theme from these games. These games are just amazing (they are my favorite), and I wanted to honor them in some way. I had a very limited time to finish the project, so I had to cut some features like the settings, the possibility to change the theme, and some others. I was also learning Java at that time (and I still do), so I had to learn a lot of things while working on this project.
This is not the original git repository because the original one contains a lot of private information that I don't want to share for now as well as all the commits.

## Installation

### Prerequisites
- Java Runtime Environment (JRE) 8 or newer
- Make sure the Java bin directory is in your system PATH

### Running the Game
1. Clone the repository:
```bash
git clone https://github.com/yourusername/SameGame.git
```

2. Navigate to the project directory:
```bash
cd SameGame
```

3. Compile the project using the Makefile:
```bash
make
```

4. Run the game:
```bash
make run
```

Alternatively, after compiling:
```bash
java Main
```

## How to Play

1. **Objective**: Clear as many blocks as possible to achieve the highest score.
2. **Controls**: Click on a group of connected blocks of the same color to remove them.
3. **Scoring**: The score increases based on the formula (n-2)², where n is the number of blocks removed.
4. **Game Over**: The game ends when no more valid moves are available.

## Project Structure

- `Main.java`: Entry point for the application
- `SameGame/`: Main package containing game components
  - `ActionListeners/`: Package for all event handlers
  - `Assets/`: Images and fonts for the game
  - `Grids/`: Predefined grid layouts
  - `SaveGame/`: Directory for save game files (grid and score)

## Documentation

The codebase is documented using Javadoc comments. This allows for easy generation of API documentation and helps in understanding the purpose and usage of different classes and methods throughout the project.
It was part of the requirements, but I maye have overdone it a bit lol.
The documentation can be generated using the following command:
```bash
javadoc -d doc Main.java -subpackages SameGame
```
This will create a `doc` directory containing the generated documentation.

## Customization

The game includes several predefined grid layouts in the `SameGame/Grids/` directory. You can create your own layouts by following the format in the existing files:
- Use 'R', 'V', and 'B' characters to represent different block colors
- Each line represents a row in the grid
- All rows must have the same number of columns
- They should always be 15 columns by 10 rows

## License

This project is academic work created as a semester project.

## Credits

Developed by Adriwin

Inspired by A Plague Tale: Innocence and A Plague Tale: Requiem.

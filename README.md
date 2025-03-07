# Horse Racing Management System - JavaFX Project

## Introduction
The **Horse Racing Management System** is a JavaFX-based application that allows users to manage horse racing events. It provides functionalities to add, update, delete, and view horse details, randomly select race participants, determine winners, and visualize results. 

## Features
- **Add Horse Details** – Users can register horses by entering details such as Horse ID, Name, Jockey Name, Age, Breed, Race Record, Group, and an Image.
- **Update Horse Details** – Users can modify the details of a registered horse.
- **Delete Horse Details** – Users can remove a horse by searching using the Horse ID.
- **View Registered Horses** – A table view displays all registered horses, sorted by Horse ID.
- **Save Horse Details to a Text File** – All changes (add, update, delete) are saved in a text file, categorized by group.
- **Select Four Horses Randomly for the Race** – The system picks one horse from each group based on the best recorded time.
- **Determine and Display Winning Horses** – Horses are assigned a random race time, and the system ranks them from 1st to 3rd place.
- **Visualize Winning Horses' Race Time** – The system displays a graphical representation of the race times.
- **Exit Program** – Users can exit the system safely.

## Technologies Used
- **Java** (Core logic and OOP principles)
- **JavaFX** (User Interface)
- **Collections Framework** (For storing and managing horse data)
- **File Handling** (For saving horse details persistently)
- **Exception Handling** (To ensure robust error-free operations)

## Installation & Setup
### Prerequisites:
- Java Development Kit (JDK 11 or later)
- JavaFX SDK configured with your IDE
- An IDE that supports JavaFX (e.g., IntelliJ IDEA, Eclipse, or NetBeans)

### Steps to Run the Project:
1. Clone the repository or download the project files.
2. Open the project in your preferred IDE.
3. Ensure JavaFX libraries are properly configured.
4. Run the `Main.java` file to launch the application.

## Functionalities & Implementation
1. **Adding Horse Details**
   - Allows users to enter horse details before starting the race.
   - Fields: Horse ID, Name, Jockey Name, Age, Breed, Race Record, Group, Image.

2. **Updating Horse Details**
   - Users can modify existing horse information.
   - Search by Horse ID is required to update details.

3. **Deleting Horse Details**
   - Users can remove a horse before the race starts by searching for its Horse ID.

4. **Viewing Registered Horses**
   - Displays all registered horses in a sorted table.
   - Includes a thumbnail image of the horse.

5. **Saving Horse Details to a File**
   - Saves and updates horse details in `horses.txt` after each modification.
   - Details are categorized by group.

6. **Selecting Four Horses for the Major Round**
   - Picks one horse from each group based on the best recorded time.
   - Displays selected horses’ details.

7. **Displaying Winning Horses**
   - Assigns a random race time (0–90s) to each horse.
   - Determines 1st, 2nd, and 3rd place based on the fastest time.

8. **Visualizing Race Times**
   - Displays a graph showing each horse’s race completion time.

9. **Exit the Program**
   - Closes the application safely.



package org.example.cw1601java.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;

import java.io.*;
import java.net.URL;
import java.util.*;
import java.util.regex.Pattern;

public class FrontEndController implements Initializable {
    public Pane HorsePane;
    public Pane RacePane;
    public Label MainTitle;
    public JFXTextField IdTextField;
    public JFXTextField NameTextField;
    public JFXTextField AgeTextField;
    public JFXTextField BreedTextField;
    public JFXTextField JockeyTextField;
    public JFXTextField ParticipatedRacesCountTextField;
    public JFXTextField WonRacesCountTextField;
    public JFXTextField GroupTextField;
    public JFXButton AddHorseButton;
    public JFXButton UpdateHorseButton;
    public JFXButton DeleteHorseButton;
    public JFXButton ClearHorseButton;
    public JFXButton SaveHorseDataFile;
    public JFXButton PaneHorseSelector;
    public JFXButton PaneRaceSelector;
    public JFXButton PaneExitApplication;
    public TableView<Horse> ViewHorsesTable;
    public TableColumn<Horse, Integer> TableColumnID;
    public TableColumn<Horse, String> TableColumnName;
    public TableColumn<Horse, Integer> TableColumnAge;
    public TableColumn<Horse, String> TableColumnBreed;
    public TableColumn<Horse, String> TableColumnJockey;
    public TableColumn<Horse, Integer> TableColumnRacesParticipatedCount;
    public TableColumn<Horse, Integer> TableColumnRacesWonCount;
    public TableColumn<Horse, String> TableColumnGroup;
    public TableColumn<Horse, Image> TableColumnImage;
    public ImageView ViewHorseImage;
    public JFXButton ImageButtonClicked;
    public JFXTextField SelectHorseFromGroupA;
    public JFXTextField SelectHorseFromGroupB;
    public JFXTextField SelectHorseFromGroupC;
    public JFXTextField SelectHorseFromGroupD;
    public JFXButton StartRaceButton;
    public JFXButton SelectWinnerButton;
    public JFXButton VisualizeWinnerTimeButton;
    public JFXTextField NameOfFirstPlace;
    public JFXTextField NameOfSecondPlace;
    public JFXTextField NameOfThirdPlace;
    public JFXTextField TimeOfFirstPlace;
    public JFXTextField TimeOfSecondPlace;
    public JFXTextField TimeOfThirdPlace;
    public BarChart VisualizingWinnerTimeBarChart;
    private HorseController horseController;
    private boolean IsRaceButtonIsOn = false;
    private List<Horse> randomHorses = new ArrayList<>();


    public FrontEndController() {this.horseController = new HorseController();}
    public void HorsePaneSelector(ActionEvent actionEvent) {
        HorsePane.setVisible(true);
        RacePane.setVisible(false);
    }
    public void RaceOnAction(ActionEvent actionEvent) {
        RacePane.setVisible(true);
        HorsePane.setVisible(false);
    }

    public void ExitOnAction(ActionEvent actionEvent) {System.exit(0);}

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        HorsePane.setVisible(true);
        RacePane.setVisible(false);

        this.horseController = new HorseController();



        TableColumnID.setCellValueFactory(new PropertyValueFactory<>("id"));
        TableColumnName.setCellValueFactory(new PropertyValueFactory<>("name"));
        TableColumnAge.setCellValueFactory(new PropertyValueFactory<>("age"));
        TableColumnBreed.setCellValueFactory(new PropertyValueFactory<>("breed"));
        TableColumnJockey.setCellValueFactory(new PropertyValueFactory<>("jockeyName"));
        TableColumnRacesParticipatedCount.setCellValueFactory(new PropertyValueFactory<>("racesParticipated"));
        TableColumnRacesWonCount.setCellValueFactory(new PropertyValueFactory<>("racesWon"));
        TableColumnGroup.setCellValueFactory(new PropertyValueFactory<>("group"));
        TableColumnImage.setCellValueFactory(new PropertyValueFactory<>("image"));
    }




    private class HorseDetailValidate {
        private static final String ID_REGEX = "^\\d{1,2}$";
        private static final String NAME_REGEX = "^[a-zA-Z]+$";
        private static final String AGE_REGEX = "^(0*[5-9]|1[0-5])$";
        private static final String BREED_REGEX = "^[a-zA-Z]+$";
        private static final String JOCKEY_NAME_REGEX = "^[a-zA-Z]+$";
        private static final String RACES_PARTICIPATED_REGEX = "^\\d{1,2}$";
        private static final String RACES_WON_REGEX = "^\\d{0,2}$";
        private static final String GROUP_REGEX = "^[A-D]$";

        public static boolean validateID(String id) {
            if (!Pattern.matches(ID_REGEX, id)) {
                showAlert("Error", "Invalid Horse ID format. It should contains integer only.");
                return false;
            }
            return true;
        }

        public static boolean validateName(String name) {
            if (!Pattern.matches(NAME_REGEX, name)) {
                showAlert("Error", "Invalid Horse name format.");
                return false;
            }
            return true;
        }

        public static boolean validateAge(String age) {
            if (!Pattern.matches(AGE_REGEX, age)) {
                showAlert("Error", "Invalid Horse age format. It should be a number between 5 and 15.");
                return false;
            }
            return true;
        }

        public static boolean validateBreed(String breed) {
            if (!Pattern.matches(BREED_REGEX, breed)) {
                showAlert("Error", "Invalid Horse breed format. It should contain alphabetical letters only.");
                return false;
            }
            return true;
        }

        public static boolean validateJockeyName(String jockeyName) {
            if (!Pattern.matches(JOCKEY_NAME_REGEX, jockeyName)) {
                showAlert("Error", "Invalid Jockey name format. It should contain alphabetical  letters only.");
                return false;
            }
            return true;
        }

        public static boolean validateRacesParticipated(String racesParticipated) {
            if (!Pattern.matches(RACES_PARTICIPATED_REGEX, racesParticipated)) {
                showAlert("Error", "Invalid number of races participated. It should be a number between 1 and 100.");
                return false;
            }
            return true;
        }

        public static boolean validateRacesWon(String racesWon, String racesParticipated) {
            if (!Pattern.matches(RACES_WON_REGEX, racesWon)) {
                showAlert("Error", "Invalid number of races won. It should be a integer.");
                return false;
            }
            int won = Integer.parseInt(racesWon);
            int participated = Integer.parseInt(racesParticipated);
            if (won > participated) {
                showAlert("Error", "Number of races won cannot exceed the number of races participated.");
                return false;
            }
            return true;
        }

        public static boolean validateGroup(String group) {
            if (!Pattern.matches(GROUP_REGEX, group)) {
                showAlert("Error", "Invalid group format. It should be a letter from A - D.");
                return false;
            }
            return true;
        }
    }

    public void AddHorseWhenEntered(ActionEvent actionEvent) {
        if (IsRaceButtonIsOn) {
            showAlert("Error", "Cannot add horse. Race has already started.");
            return;
        }

        if (IdTextField.getText().isEmpty() || NameTextField.getText().isEmpty() || AgeTextField.getText().isEmpty() || BreedTextField.getText().isEmpty() || JockeyTextField.getText().isEmpty() || ParticipatedRacesCountTextField.getText().isEmpty() || WonRacesCountTextField.getText().isEmpty() || GroupTextField.getText().isEmpty()) {
            showAlert("Error", "Please fill in all fields and select an image .");
            return;
        }

        if (horseController.getHorses().size() >= 20) {
            showAlert("Error", "Cannot add more horses. Maximum limit (20) reached.");
            return;
        }

        String group = GroupTextField.getText().toUpperCase();

        if (!group.matches("[A-D]")) {
            showAlert("Error", "Invalid group specified. Please choose a group from A - D.");
            return;
        }

        long groupCount = horseController.getHorses().stream().filter(horse -> horse.getGroup().equals(group)).count();
        if (groupCount >= 5) {
            showAlert("Error", "Cannot add more horses to Group " + group + ". Maximum limit (5) reached for this group.");
            return;
        }

        // Proceed with horse addition
        String id = IdTextField.getText();
        String name = NameTextField.getText();
        String age = AgeTextField.getText();
        String breed = BreedTextField.getText();
        String jockeyName = JockeyTextField.getText();
        String racesParticipated = ParticipatedRacesCountTextField.getText();
        String racesWon = WonRacesCountTextField.getText();
        Image image = ViewHorseImage.getImage();

        if (!isIdValidate(id)) {
            showAlert("Error", "Invalid Horse ID format. It should contains integer only.");
            return;
        }

        if(!isAgeValidate(age)){
            showAlert("Error", "Invalid Horse age format. It should be a number between 5 and 15.");
            return;
        }

        if(!isRaceParticipated(racesParticipated)){
            showAlert("Error", "Invalid number of races participated. It should be a number between 1 and 100.");
            return;
        }

        if( !isRaceWon(racesWon)){
            showAlert("Error", "Invalid number of races participated. It should be a number between 1 and 100.");
            return;
        }

        // Check if the ID is unique and does  not Repeat
        if (horseController.getHorses().stream().anyMatch(horse -> horse.getId() == Integer.parseInt(id))) {
            showAlert("Error", "ID already exists. Please select  a different ID.");
            return;
        }

        if (HorseDetailValidate.validateID(id)
                && HorseDetailValidate.validateName(name)
                && HorseDetailValidate.validateAge(age)
                && HorseDetailValidate.validateBreed(breed)
                && HorseDetailValidate.validateJockeyName(jockeyName)
                && HorseDetailValidate.validateRacesParticipated(racesParticipated)
                && HorseDetailValidate.validateRacesWon(racesWon, racesParticipated)
                && HorseDetailValidate.validateGroup(group)) {
            int intID = Integer.parseInt(id);
            int intAge = Integer.parseInt(age);
            int intRacesParticipated = Integer.parseInt(racesParticipated);
            int intRacesWon = Integer.parseInt(racesWon);

            Horse newHorse = new Horse(intID, name, intAge, breed, jockeyName, intRacesParticipated, intRacesWon, group, image);
            horseController.addHorse(newHorse);
            showAlert("", "Horse added successfully.");
            ViewHorsesTableView();
            ClearTextFieldsMethod();
        }
    }

    private boolean isIdValidate(String str) {
        return str.matches("^\\d{1,2}$");
    }

    private boolean isAgeValidate(String str) {
        return str.matches("^(0*[5-9]|1[0-5])$");
    }

    private boolean isRaceParticipated(String str) {
        return str.matches("^\\d{1,2}$");
    }

    private boolean isRaceWon(String str) {
        return str.matches("^\\d{1,2}$");
    }

    private void ClearTextFieldsMethod() {
        IdTextField.clear();
        NameTextField.clear();
        AgeTextField.clear();
        BreedTextField.clear();
        JockeyTextField.clear();
        ParticipatedRacesCountTextField.clear();
        WonRacesCountTextField.clear();
        GroupTextField.clear();
        ViewHorseImage.setImage(null);
    }

    public void UpdateHorseWhenSelected(ActionEvent actionEvent) {
        if (IsRaceButtonIsOn) {
            showAlert("Error", "Cannot update horse. Race has already started.");
            return;
        }

        Horse selectedHorse = ViewHorsesTable.getSelectionModel().getSelectedItem();

        if (selectedHorse != null) {
            String id = IdTextField.getText();
            String name = NameTextField.getText();
            String age = AgeTextField.getText();
            String breed = BreedTextField.getText();
            String jockeyName = JockeyTextField.getText();
            String racesParticipated = ParticipatedRacesCountTextField.getText();
            String racesWon = WonRacesCountTextField.getText();
            String group = GroupTextField.getText();
            Image image = ViewHorseImage.getImage();

            if (!isIdValidate(id)) {
                showAlert("Error", "Invalid Horse ID format. It should contains integer only.");
                return;
            }

            if(!isAgeValidate(age)){
                showAlert("Error", "Invalid Horse age format. It should be a number between 5 and 15.");
                return;
            }

            if(!isRaceParticipated(racesParticipated)){
                showAlert("Error", "Invalid number of races participated. It should be a number between 1 and 100.");
                return;
            }

            if( !isRaceWon(racesWon)){
                showAlert("Error", "Invalid number of races participated. It should be a number between 1 and 100.");
                return;
            }

            if (HorseDetailValidate.validateID(id)
                    && HorseDetailValidate.validateName(name)
                    && HorseDetailValidate.validateAge(age)
                    && HorseDetailValidate.validateBreed(breed)
                    && HorseDetailValidate.validateJockeyName(jockeyName)
                    && HorseDetailValidate.validateRacesParticipated(racesParticipated)
                    && HorseDetailValidate.validateRacesWon(racesWon, racesParticipated)
                    && HorseDetailValidate.validateGroup(group)) {
                int intID = Integer.parseInt(id);
                int intAge = Integer.parseInt(age);
                int intRacesParticipated = Integer.parseInt(racesParticipated);
                int intRacesWon = Integer.parseInt(racesWon);

                Horse updatedHorse = new Horse(intID, name, intAge, breed, jockeyName, intRacesParticipated, intRacesWon, group, image);

                horseController.updateHorse(updatedHorse);

                showAlert("Success", "Horse details updated successfully.");

                ViewHorsesTableView();
                ClearTextFieldsMethod();
            }
        } else {
            showAlert("Error", "Please select a horse to update from the table.");
        }
    }
//deleting the horse
    public void DeleteHorseWhenSelected(ActionEvent actionEvent) {
        if (IsRaceButtonIsOn) {
            showAlert("Error", "Cannot delete horse. Race has already started.");
            return;
        }

        Horse selectedHorse = ViewHorsesTable.getSelectionModel().getSelectedItem();

        if (selectedHorse == null) {
            showAlert("Error", "Please select a  horse to be deleted from the table .");
            return;
        }

        //Fill  the text fields with the information of  the selected horse
        IdTextField.setText(String.valueOf(selectedHorse.getId()));
        NameTextField.setText(selectedHorse.getName());
        AgeTextField.setText(String.valueOf(selectedHorse.getAge()));
        BreedTextField.setText(selectedHorse.getBreed());
        JockeyTextField.setText(selectedHorse.getJockeyName());
        ParticipatedRacesCountTextField.setText(String.valueOf(selectedHorse.getRacesParticipated()));
        WonRacesCountTextField.setText(String.valueOf(selectedHorse.getRacesWon()));
        GroupTextField.setText(selectedHorse.getGroup());

        horseController.deleteHorse(selectedHorse);
        showAlert("Success", "Horse deleted successfully.");
        ViewHorsesTableView();
        ClearTextFieldsMethod();
    }

    private void ViewHorsesTableView() {
        ViewHorsesTable.getColumns().clear();

        ViewHorsesTable.getItems().clear();

        horseController.sortHorsesById();

        TableColumnImage.setCellFactory(column -> {
            return new TableCell< Horse, Image>() {
                private final ImageView imageView = new ImageView();

                @Override
                protected void updateItem(Image image, boolean empty) {
                    super.updateItem(image, empty);

                    if (image == null || empty) {
                        setGraphic(null);
                    } else {
                        imageView.setImage(image);
                        imageView.setFitWidth(70);
                        imageView.setFitHeight(70);
                        setGraphic(imageView);
                    }
                }
            };
        });

        //  Appending the necessary columns to the table view
        ViewHorsesTable.getColumns().addAll(TableColumnID, TableColumnName, TableColumnAge, TableColumnBreed, TableColumnJockey, TableColumnRacesParticipatedCount, TableColumnRacesWonCount, TableColumnGroup, TableColumnImage);


        ViewHorsesTable.getItems().addAll(horseController.getHorses());
    }

    private static void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    public void ClearTextFields(ActionEvent actionEvent) {
        IdTextField.clear();
        NameTextField.clear();
        AgeTextField.clear();
        BreedTextField.clear();
        JockeyTextField.clear();
        ParticipatedRacesCountTextField.clear();
        WonRacesCountTextField.clear();
        GroupTextField.clear();
        ViewHorseImage.setImage(null);
    }
    //Saving the horse details to the text file called Horse_details
    public void SaveToFileWhenDataEntered(ActionEvent actionEvent) {
        List<Horse>HorseList=horseController.getHorses();
        try {
            File file = new File("Horse_details.txt");
            FileWriter writer = new FileWriter(file);
            BufferedWriter bufferedWriter = new BufferedWriter(writer);

            // Iterate over unique groups
            Set<String> uniqueGroups = new HashSet<>();
            for (Horse horse : HorseList) {
                uniqueGroups.add(horse.getGroup());
            }

            // Write horse details of group
            for (String group : uniqueGroups) {
                bufferedWriter.write("Group " + group + ":\n");
                for (Horse horse : HorseList) {
                    if (horse.getGroup().equals(group)) {
                        bufferedWriter.write("Horse ID : " + horse.getId() + ",");
                        bufferedWriter.write(" Name : " + horse.getName() + ",");
                        bufferedWriter.write(" Age : " + horse.getAge() + ",");
                        bufferedWriter.write(" Breed : " + horse.getBreed() + ",");
                        bufferedWriter.write(" Jockey Name : " + horse.getJockeyName() + ",");
                        bufferedWriter.write(" Races Participated : " + horse.getRacesParticipated() + ",");
                        bufferedWriter.write(" Races Won : " + horse.getRacesWon() + ",");
                        bufferedWriter.write("Image Path : " + horse.getImage() + "\n");
                    }
                }
                bufferedWriter.write("\n");
            }

            bufferedWriter.close();
            showAlert("HorseDetails.txt", "File Saved Successfully.");
        } catch (IOException e) {
            e.printStackTrace();
            showAlert("HorseDetails.txt", "File Save was Unsuccessful.");
        }
    }

    public void TableClickToSelectRowOfData(MouseEvent mouseEvent) {
        Horse selectedHorse = ViewHorsesTable.getSelectionModel().getSelectedItem();

        IdTextField.setText(String.valueOf(selectedHorse.getId()));
        NameTextField.setText(selectedHorse.getName());
        AgeTextField.setText(String.valueOf(selectedHorse.getAge()));
        BreedTextField.setText(selectedHorse.getBreed());
        JockeyTextField.setText(selectedHorse.getJockeyName());
        ParticipatedRacesCountTextField.setText(String.valueOf(selectedHorse.getRacesParticipated()));
        WonRacesCountTextField.setText(String.valueOf(selectedHorse.getRacesWon()));
        GroupTextField.setText(selectedHorse.getGroup());
        if (selectedHorse.getImage() != null) {
            ViewHorseImage.setImage(selectedHorse.getImage());
        }
    }
    //adding the image when taking the horse details
    public void AddImageToTheHorseClass(ActionEvent actionEvent) {
        ImageButtonClicked.setOnAction(event -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Select Image");

            File initialDirectory = new File(System.getProperty("user.home"));
            fileChooser.setInitialDirectory(initialDirectory);

            FileChooser.ExtensionFilter imageFilter = new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif");
            fileChooser.getExtensionFilters().add(imageFilter);

            File selectedFile = fileChooser.showOpenDialog(ImageButtonClicked.getScene().getWindow());

            if (selectedFile != null) {
                Image image = new Image(selectedFile.toURI().toString());
                ViewHorseImage.setImage(image);
            }
        });
    }
// selecting four horses randomly and stating the race
    public void StartRaceAndSetHorsesForTheRace(ActionEvent actionEvent) {
        if (IsRaceButtonIsOn) {
            showAlert("Error", "Cannot select random horses. Race has already started.");
            return;
        }

        // make raceStarted flag to true
        IsRaceButtonIsOn = true;

        // Check if each group has at least one horse in them
        boolean groupAExists = false;
        boolean groupBExists = false;
        boolean groupCExists = false;
        boolean groupDExists = false;

        // Iterate through the horses to check their groups and select random horses for each group
        List<Horse> groupAHorses = new ArrayList<>();
        List<Horse> groupBHorses = new ArrayList<>();
        List<Horse> groupCHorses = new ArrayList<>();
        List<Horse> groupDHorses = new ArrayList<>();

        for (Horse horse : horseController.getHorses()) {
            switch (horse.getGroup()) {
                case "A":
                    groupAExists = true;
                    groupAHorses.add(horse);
                    break;
                case "B":
                    groupBExists = true;
                    groupBHorses.add(horse);
                    break;
                case "C":
                    groupCExists = true;
                    groupCHorses.add(horse);
                    break;
                case "D":
                    groupDExists = true;
                    groupDHorses.add(horse);
                    break;
            }
        }

        // If one of the group does not have atleast one horse show the error message
        if (!(groupAExists && groupBExists && groupCExists && groupDExists)) {
            showAlert("Error", "Each group must have at least one member.");
            IsRaceButtonIsOn = false;
            return;
        }
        //randomly selected 4 horses are added to the randomHorses List

        Random random = new Random();
        Horse selectedGroupAHorse = groupAHorses.get(random.nextInt(groupAHorses.size()));
        Horse selectedGroupBHorse = groupBHorses.get(random.nextInt(groupBHorses.size()));
        Horse selectedGroupCHorse = groupCHorses.get(random.nextInt(groupCHorses.size()));
        Horse selectedGroupDHorse = groupDHorses.get(random.nextInt(groupDHorses.size()));

        // Set the text of the JFXTextField components with the details of the selected horses
        SelectHorseFromGroupA.setText(selectedGroupAHorse.toString());
        SelectHorseFromGroupB.setText(selectedGroupBHorse.toString());
        SelectHorseFromGroupC.setText(selectedGroupCHorse.toString());
        SelectHorseFromGroupD.setText(selectedGroupDHorse.toString());

        // Adding  horses to the randomHorses list
        randomHorses.add(selectedGroupAHorse);
        randomHorses.add(selectedGroupBHorse);
        randomHorses.add(selectedGroupCHorse);
        randomHorses.add(selectedGroupDHorse);
    }

    public void SelectFirstSecondThirdPlacesOfTheRace(ActionEvent actionEvent) {
        if (!IsRaceButtonIsOn || randomHorses.isEmpty()) {
            showAlert("Error", " No random horses selected and race has not started yet .");
            return;
        }

        Random random = new Random();
        for (Horse horse : randomHorses) {
            int raceTime = random.nextInt(81) + 10;
            horse.setRaceTime(raceTime);
        }

        int n = randomHorses.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (randomHorses.get(j).getRaceTime() > randomHorses.get(j + 1).getRaceTime()) {
                    Horse temp = randomHorses.get(j);
                    randomHorses.set(j, randomHorses.get(j + 1));
                    randomHorses.set(j + 1, temp);
                }
            }
        }

        // Show the  race results
        StringBuilder raceResultDetails = new StringBuilder("Race Results:\n");
        for (int i = 0; i < Math.min(randomHorses.size(), 3); i++) {
            int place = i + 1;
            String horseName = randomHorses.get(i).getName();
            int raceTime = randomHorses.get(i).getRaceTime();
            String formattedRaceTime = raceTime + "s";
            raceResultDetails.append(place).append(". ").append(horseName).append(" - Time: ").append(formattedRaceTime).append("\n");

            switch (place) {
                case 1:
                    NameOfFirstPlace.setText(horseName);
                    TimeOfFirstPlace.setText(formattedRaceTime);
                    break;
                case 2:
                    NameOfSecondPlace.setText(horseName);
                    TimeOfSecondPlace.setText(formattedRaceTime);
                    break;
                case 3:
                    NameOfThirdPlace.setText(horseName);
                    TimeOfThirdPlace.setText(formattedRaceTime);
                    break;
            }
        }
        showAlert("Race Results", raceResultDetails.toString());
    }

    public void VisualizeFirstSecondThirdPlacesTimeOfTheRace(ActionEvent actionEvent) {
        // Check if the race has been started and winners have been selected
        if (NameOfFirstPlace.getText().isEmpty() || NameOfSecondPlace.getText().isEmpty() || NameOfThirdPlace.getText().isEmpty()) {
            showAlert("Error", "Cannot visualize race times. Winners have not been selected yet.");
            return;
        }

        // Clear existing data in the bar chart
        VisualizingWinnerTimeBarChart.getData().clear();

        // Create series for each winner
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.getData().add(new XYChart.Data<>("First Place", Integer.parseInt(TimeOfFirstPlace.getText().replaceAll("[^\\d]", ""))));
        series.getData().add(new XYChart.Data<>("Second Place", Integer.parseInt(TimeOfSecondPlace.getText().replaceAll("[^\\d]", ""))));
        series.getData().add(new XYChart.Data<>("Third Place", Integer.parseInt(TimeOfThirdPlace.getText().replaceAll("[^\\d]", ""))));

        // Add series to the bar chart
        VisualizingWinnerTimeBarChart.getData().add(series);
    }

    public class Horse {
        private int Id;
        private String Name;
        private int Age;
        private String Breed;
        private String JockeyName;
        private int RacesParticipated;
        private int RacesWon;
        private String Group;
        private Image image;
        private int raceTime;

        public int getId() {
            return Id;
        }

        public void setId(int id) {
            Id = id;
        }

        public String getName() {
            return Name;
        }

        public void setName(String name) {
            Name = name;
        }

        public int getAge() {
            return Age;
        }

        public void setAge(int age) {
            Age = age;
        }

        public String getBreed() {
            return Breed;
        }

        public void setBreed(String breed) {
            Breed = breed;
        }

        public String getJockeyName() {
            return JockeyName;
        }

        public void setJockeyName(String jockeyName) {
            JockeyName = jockeyName;
        }

        public int getRacesParticipated() {
            return RacesParticipated;
        }

        public void setRacesParticipated(int racesParticipated) {
            RacesParticipated = racesParticipated;
        }

        public int getRacesWon() {
            return RacesWon;
        }

        public void setRacesWon(int racesWon) {
            RacesWon = racesWon;
        }

        public String getGroup() {
            return Group;
        }

        public void setGroup(String group) {
            Group = group;
        }

        public Image getImage() {
            return image;
        }

        public void setImage(Image image) {
            this.image = image;
        }

        public int getRaceTime() {
            return raceTime;
        }

        public void setRaceTime(int raceTime) {
            this.raceTime = raceTime;
        }

        @Override
        public String toString() {
            return String.format("ID : " + Id + ", Name : " + Name + ", Age : " + Age + ", Breed : " + Breed + ", Jockey Name : " + JockeyName + ", Races Participated : " + RacesParticipated + ", Races Won : " + RacesWon);
        }

        public Horse() {}

        public Horse(int id, String name, int age, String breed, String jockeyName, int racesParticipated, int racesWon, String group, Image image) {
            Id = id;
            Name = name;
            Age = age;
            Breed = breed;
            JockeyName = jockeyName;
            RacesParticipated = racesParticipated;
            RacesWon = racesWon;
            Group = group;
            this.image = image;
        }

        public Horse(int id, String name, int age, String breed, String jockeyName, int racesParticipated, int racesWon, String group) {
            Id = id;
            Name = name;
            Age = age;
            Breed = breed;
            JockeyName = jockeyName;
            RacesParticipated = racesParticipated;
            RacesWon = racesWon;
            Group = group;
        }
    }

    public class HorseController{
        private final List<Horse> horses;

        public HorseController() {
            horses = new ArrayList<>();
        }

        public List<Horse> getHorses() {
            return horses;
        }
        public void addHorse(Horse horse) {horses.add(horse);}
        public void deleteHorse(Horse horse) {
            horses.remove(horse);
        }

        public void sortHorsesById() {
            mergeSort(horses, 0, horses.size() - 1);
        }

        private void mergeSort(List<Horse> list, int left, int right) {
            if (left < right) {
                int middle = (left + right) / 2;
                mergeSort(list, left, middle);
                mergeSort(list, middle + 1, right);
                merge(list, left, middle, right);
            }
        }

        private void merge(List<Horse> list, int left, int middle, int right) {
            int n1 = middle - left + 1;
            int n2 = right - middle;

            List<Horse> leftList = new ArrayList<>();
            List<Horse> rightList = new ArrayList<>();

            for (int i = 0; i < n1; ++i)
                leftList.add(list.get(left + i));
            for (int j = 0; j < n2; ++j)
                rightList.add(list.get(middle + 1 + j));

            int i = 0, j = 0;
            int k = left;
            while (i < n1 && j < n2) {
                if (leftList.get(i).getId() <= rightList.get(j).getId()) {
                    list.set(k, leftList.get(i));
                    i++;
                } else {
                    list.set(k, rightList.get(j));
                    j++;
                }
                k++;
            }

            while (i < n1) {
                list.set(k, leftList.get(i));
                i++;
                k++;
            }

            while (j < n2) {
                list.set(k, rightList.get(j));
                j++;
                k++;
            }
        }

        public void updateHorse(Horse updatedHorse) {
            // Find the horse with the same ID as the updatedHorse
            for (Horse horse : horses) {
                if (horse.getId() == updatedHorse.getId()) {
                    // Update the horse's attributes with the new values
                    horse.setName(updatedHorse.getName());
                    horse.setAge(updatedHorse.getAge());
                    horse.setBreed(updatedHorse.getBreed());
                    horse.setJockeyName(updatedHorse.getJockeyName());
                    horse.setRacesParticipated(updatedHorse.getRacesParticipated());
                    horse.setRacesWon(updatedHorse.getRacesWon());
                    horse.setGroup(updatedHorse.getGroup());
                    horse.setImage(updatedHorse.getImage());
                    return;
                }
            }
        }
    }
}

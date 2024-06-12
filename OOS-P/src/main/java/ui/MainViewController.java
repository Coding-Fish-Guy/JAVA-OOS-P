package ui;

import bank.exceptions.AccountAlreadyExistsException;
import bank.exceptions.AccountDoesNotExistException;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.Optional;

public class MainViewController implements EventHandler<ActionEvent> {
    Main_Application main_application;
    @FXML
    private javafx.scene.control.ListView<String> listaccounts;
    @FXML
    protected ContextMenu contextMenu = new ContextMenu();
    @FXML
    protected MenuItem menuItemAuswaehlen = new MenuItem("Auswählen");
    @FXML
    protected MenuItem menuItemLoeschen = new MenuItem("Löschen");
    @FXML
    protected Button createAccountButton = new Button();

    /**
     * Methode , die den account erstellt.
     *
     * @param acc account der erstellt werden soll
     * @throws AccountAlreadyExistsException
     */
    private void Creation(String acc) throws AccountAlreadyExistsException {
        main_application.getBank().createAccount(acc);
        this.listaccounts.getItems().add(acc);
    }

    /**
     * Löscht den Account
     *
     * @throws AccountDoesNotExistException
     */
    private void Delete() throws AccountDoesNotExistException {
        int selectedIndex = this.listaccounts.getSelectionModel().getSelectedIndex();
        String deletedAcc = this.listaccounts.getSelectionModel().getSelectedItem();
        listaccounts.getItems().remove(selectedIndex);
        main_application.getBank().deleteAccount(deletedAcc);
    }

    /**
     * setzt die MainView beim Aufruf mit allen Elementen.
     *
     * @param main_application setzt das Objekt auf die main_application
     */
    @FXML
    public void setMain_application(Main_Application main_application) {
        this.main_application = main_application;
        listaccounts.setItems(main_application.getaccountsList());
        contextMenu.getItems().addAll(menuItemAuswaehlen, menuItemLoeschen);
        listaccounts.setContextMenu(contextMenu);
    }

    /**
     * Methode die alle Events abfängt und behandelt.
     *
     * @param actionEvent trigger event as a Parameter
     */
    @Override
    public void handle(ActionEvent actionEvent) {
        if (actionEvent.getSource() == menuItemAuswaehlen) {
            String selectedAccount = this.listaccounts.getSelectionModel().getSelectedItem();
            this.main_application.showAccountView(selectedAccount);
        } else if (actionEvent.getSource() == menuItemLoeschen) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Account löschen");
            alert.setHeaderText("Achtung, Account löschen !!!");
            alert.setContentText("Sind Sie sich sicher ?");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                try {
                    Delete();
                } catch (AccountDoesNotExistException e) {
                    System.err.println(e.getMessage());
                }
            } else {
                alert.close();
            }
        } else if (actionEvent.getSource() == createAccountButton) {
            TextInputDialog dialog = new TextInputDialog();
            dialog.setTitle("Account Erstellung");
            dialog.setHeaderText("Erstellen sie ihren Account");
            dialog.setContentText("Bitte geben sie ihren Accountnamen ein:");
            dialog.showAndWait().ifPresent(acc -> {
                try {
                    Creation(acc);
                } catch (AccountAlreadyExistsException e) {
                    alertAccountDoesExist();
                    System.err.println(e.getMessage());
                }
            });
        }
    }

    /**
     * Fehlermeldung Dialog, wenn account nicht existiert.
     */
    public void alertAccountDoesExist() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Fehler");
        alert.setHeaderText("Es ist ein Fehler aufgetreten");
        alert.setContentText("Account existiert nicht");
        alert.showAndWait();
    }
}

package experia.coffee.experiacoffee.data;
import experia.coffee.experiacoffee.model.Ticket;
import experia.coffee.experiacoffee.utils.PopupWindowError;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.util.Date;
import experia.coffee.experiacoffee.common.Constants;

public class TicketQuery {

    private final DBConnection c = new DBConnection();

    public boolean createTicket(String ticketTile, String title, String createdBy ,Date creationDate) {
        try {
            c.getDBConn();
            String query = "INSERT INTO `tbl_ticketing` " +
                    "(`TITOLO`, `DESCRIZIONE`, `CREATO_DA`, `DATA_CREAZIONE`) " +
                    "VALUES (?, ?, ?, ?)";

            try (PreparedStatement preparedStatement = DBConnection.getCon().prepareStatement(query)) {
                preparedStatement.setString(1, ticketTile);
                preparedStatement.setString(2, title);
                preparedStatement.setString(3, createdBy);
                preparedStatement.setString(4, new Timestamp(creationDate.getTime()).toString());
                preparedStatement.executeUpdate();
                return true;
            } catch (Exception e) {
                PopupWindowError.handleException(e);
            } finally {
                DBConnection.closeConnection();
            }
        } catch (Exception e) {
            PopupWindowError.handleException(e);
            return false;
        } finally {
            DBConnection.closeConnection();
        }
        return false;
    }

    public ObservableList<Ticket> getTicketList () {
        ObservableList<Ticket> ticketList = FXCollections.observableArrayList();
        try {
            String query = "SELECT ID, TITOLO, DESCRIZIONE, GESTITO_DA, CREATO_DA, STATO FROM tbl_ticketing ORDER BY ID ASC";
            c.getDBConn();
            Statement st = DBConnection.getCon().createStatement();
            ResultSet rs = st.executeQuery(query);
            experia.coffee.experiacoffee.model.Ticket s;
            while (rs.next()) {
                s = new experia.coffee.experiacoffee.model.Ticket(rs.getInt("ID"),rs.getString("TITOLO"), rs.getString("DESCRIZIONE"),rs.getString("GESTITO_DA"),rs.getString("CREATO_DA"), rs.getString("STATO"));
                ticketList.add(s);
            }
            rs.close();
            st.close();
            DBConnection.closeConnection();
        } catch (Exception e) {
            PopupWindowError.handleException(e);
        } finally {
            DBConnection.closeConnection();
        }
        return ticketList;
    }

    public ObservableList<Ticket> getTicketListByEmail(String email) {
        ObservableList<Ticket> ticketList = FXCollections.observableArrayList();
        try {
            c.getDBConn();
            String query = "SELECT ID, TITOLO, GESTITO_DA, STATO FROM tbl_ticketing WHERE CREATO_DA = ? ORDER BY ID ASC";
            try (PreparedStatement preparedStatement = DBConnection.getCon().prepareStatement(query)) {

                preparedStatement.setString(1, email);

                try (ResultSet rs = preparedStatement.executeQuery()) {
                    while (rs.next()) {
                        experia.coffee.experiacoffee.model.Ticket t;
                        t = new experia.coffee.experiacoffee.model.Ticket(rs.getInt("ID"), rs.getString("TITOLO"), rs.getString("GESTITO_DA"), rs.getString("STATO"));
                        ticketList.add(t);
                    }
                } catch (Exception e) {
                    PopupWindowError.handleException(e);
                } finally {
                    DBConnection.closeConnection();
                }
            } catch (Exception e) {
                PopupWindowError.handleException(e);
            }
        } catch (Exception e) {
            PopupWindowError.handleException(e);
        }
        return ticketList;
    }

    public Integer assignTicket(String emailDipendente,Integer ticketID) {
        try {
            String query = "UPDATE tbl_ticketing SET GESTITO_DA = ?, STATO = ? WHERE ID = ?";
            c.getDBConn();

            try (PreparedStatement preparedStatement = DBConnection.getCon().prepareStatement(query)) {
                preparedStatement.setString(1, emailDipendente);
                preparedStatement.setString(2, Constants.TICKET_STATUS_TAKEN_CHARDE);
                preparedStatement.setInt(3, ticketID);
                int rowsAffected = preparedStatement.executeUpdate();
                return rowsAffected;

            } catch (SQLException e) {
                PopupWindowError.handleException(e);
            } finally {
                DBConnection.closeConnection();
            }
        } catch (Exception e) {
            PopupWindowError.handleException(e);
        }
        return 0;
    }

    public Integer updateTicketStatusById(Integer ticketID, String ticketStatus) {
        try {
            String query = "UPDATE tbl_ticketing SET STATO = ? WHERE ID = ?";
            c.getDBConn();

            try (PreparedStatement preparedStatement = DBConnection.getCon().prepareStatement(query)) {
                preparedStatement.setString(1, ticketStatus);
                preparedStatement.setString(2, ticketID.toString());
                int rowsAffected = preparedStatement.executeUpdate();
                return rowsAffected;
            } catch (SQLException e) {
                PopupWindowError.handleException(e);
            } finally {
                DBConnection.closeConnection();
            }
        } catch (Exception e) {
            PopupWindowError.handleException(e);
        }
        return 0;
    }

}

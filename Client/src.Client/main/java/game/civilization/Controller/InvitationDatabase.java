package game.civilization.Controller;

import game.civilization.Model.Invitation;

import java.util.ArrayList;

public class InvitationDatabase {
    private static InvitationDatabase instance;
    private ArrayList<Invitation> allInvitations = new ArrayList<>();

    private InvitationDatabase() {

    }

    public static InvitationDatabase getInstance() {
        if (instance == null)
            instance = new InvitationDatabase();
        return instance;
    }


    public ArrayList<Invitation> getAllInvitations() {
        return allInvitations;
    }

    public void setAllInvitations(ArrayList<Invitation> allInvitations) {
        this.allInvitations = allInvitations;
    }

    public void addInvitation(Invitation invitation){
        allInvitations.add(invitation);
    }

    public ArrayList<Invitation> getInvitationBySender(String username){
        ArrayList<Invitation> invitations = new ArrayList<>();
        for (Invitation invitation : allInvitations) {
            if (invitation.getUsername1().equals(username))
                invitations.add(invitation);
        }
        return invitations;
    }

    public ArrayList<Invitation> getInvitationByReceiver(String username){
        ArrayList<Invitation> invitations = new ArrayList<>();
        for (Invitation invitation : allInvitations) {
            if (invitation.getUsername2().equals(username))
                invitations.add(invitation);
        }
        return invitations;
    }

    public void removeInvitation(Invitation invitation){
        for (Invitation allInvitation : allInvitations) {
            if (allInvitation.getUsername1().equals(invitation.getUsername1()) && allInvitation.getUsername2().equals(invitation.getUsername2()) && allInvitation.getGameId().equals(invitation.getGameId())){
                allInvitations.remove(allInvitation);
                return;
            }
        }
    }
}

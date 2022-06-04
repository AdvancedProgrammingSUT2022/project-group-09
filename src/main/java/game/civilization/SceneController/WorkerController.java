package game.civilization.SceneController;

public class WorkerController {
    private static WorkerController WorkerController = null;

    public static WorkerController getInstance() {
        if (WorkerController == null)
            WorkerController = new WorkerController();
        return WorkerController;
    }

    private WorkerController() {

    }


}

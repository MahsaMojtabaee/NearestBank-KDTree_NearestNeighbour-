class QueueNode{
    Point point;
    String name;
    String MainBankName;
    QueueNode next;
    QueueNode(String MainBankName, String name, double x, double y){
        Point point = new Point(x,y);
        this.MainBankName = MainBankName;
        this.name = name;
        this.point = point;
        next = null;
    }
}
public class Queue {
    QueueNode front, rear;

    public Queue()
    {
        this.front = this.rear = null;
    }

    // Method to add an key to the queue.
    void enqueue(String MainBankName, String name, double x, double y)
    {

        // Create a new LL node
        QueueNode temp = new QueueNode(MainBankName, name, x, y);

        // If queue is empty, then new node is front and rear both
        if (this.rear == null) {
            this.front = this.rear = temp;
            return;
        }

        // Add the new node at the end of queue and change rear
        this.rear.next = temp;
        this.rear = temp;
    }

    // Method to remove an key from queue.
    void dequeue()
    {
        // If queue is empty, return NULL.
        if (this.front == null)
            return;

        // Store previous front and move front one node ahead
        QueueNode temp = this.front;
        this.front = this.front.next;

        // If front becomes NULL, then change rear also as NULL
        if (this.front == null)
            this.rear = null;
    }

}

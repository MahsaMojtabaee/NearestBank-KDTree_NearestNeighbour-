//public class Queue {
//    Region front, rear;
//
//    public Queue()
//    {
//        this.front = this.rear = null;
//    }
//
//    // Method to add an key to the queue.
//    void enqueue(String name, double x1, double y1, double x2, double y2, double x3, double y3, double x4, double y4)
//    {
//
//        // Create a new LL node
//        Region temp = new   Region(name, x1, y1, x2, y2, x3, y3, x4, y4);
//
//        // If queue is empty, then new node is front and rear both
//        if (this.rear == null) {
//            this.front = this.rear = temp;
//            return;
//        }
//
//        // Add the new node at the end of queue and change rear
//        this.rear.next = temp;
//        this.rear = temp;
//    }
//
//    // Method to remove an key from queue.
//    void dequeue()
//    {
//        // If queue is empty, return NULL.
//        if (this.front == null)
//            return;
//
//        // Store previous front and move front one node ahead
//        Region temp = this.front;
//        this.front = this.front.next;
//
//        // If front becomes NULL, then change rear also as NULL
//        if (this.front == null)
//            this.rear = null;
//    }
//
//}

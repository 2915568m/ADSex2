
public class DynamicSet {
    private Node self;
    private  int size =0;
    
    public DynamicSet()
    {
        this.self = null;
    }

    public void add(int element)
    {
        self = insert(self, element);
        size++;
    }
    public boolean isElement(int element)
    {
        return searchNode(self, element);
    }
    public void remove(int element)
    {
        if(isElement(element))
        {
            size --;
            self = deleteNode(self, element);
        }
    }
    public boolean setEmpty()
    {
        return size>0;
    }
    public int setSize()
    {
        return size;
    }
    public DynamicSet union(DynamicSet otherSet)
    {
        DynamicSet union = new DynamicSet();
        this.addAll(this.self, union);
        this.addAll(otherSet.self, union);
        return union;
    }
    public DynamicSet intersection(DynamicSet otherSet)
    {
        DynamicSet intersection = new DynamicSet();
        intersectElements(this.self, otherSet, intersection);
        return intersection;
    }

    // Main method for testing
    public static void main(String[] args) {
        // Creating 3 sets with different elements
        DynamicSet set1 = new DynamicSet();
        DynamicSet set2 = new DynamicSet();
        DynamicSet set3 = new DynamicSet();

        // Adding elements to the sets
        set1.add(10);
        set1.add(20);
        set1.add(30);
        set1.add(40);
        set1.add(50);

        set2.add(30);
        set2.add(40);
        set2.add(60);
        set2.add(70);

        set3.add(40);
        set3.add(50);
        set3.add(80);
        set3.add(90);

        // Print the sets
        System.out.println("Set 1:");
        set1.toString();  // Expected Output: 10 20 30 40 50

        System.out.println("Set 2:");
        set2.toString();  // Expected Output: 30 40 60 70

        System.out.println("Set 3:");
        set3.toString();  // Expected Output: 40 50 80 90

        // Test Union of Set1 and Set2
        DynamicSet unionSet1And2 = set1.union(set2);
        System.out.print("Union of Set 1 and Set 2: ");
        unionSet1And2.toString();  // Expected Output: 10 20 30 40 50 60 70

        // Test Intersection of Set1 and Set2
        DynamicSet intersectionSet1And2 = set1.intersection(set2);
        System.out.print("Intersection of Set 1 and Set 2: ");
        intersectionSet1And2.toString();  // Expected Output: 30 40

        // Test Union of all three sets (Set1, Set2, Set3)
        DynamicSet unionSetAll = set1.union(set2).union(set3);
        System.out.print("Union of Set 1, Set 2, and Set 3: ");
        unionSetAll.toString();  // Expected Output: 10 20 30 40 50 60 70 80 90

        // Test Intersection of Set1, Set2, and Set3
        DynamicSet intersectionSetAll = set1.intersection(set2).intersection(set3);
        System.out.print("Intersection of Set 1, Set 2, and Set 3: ");
        intersectionSetAll.toString();  // Expected Output: 40 50
    }



    //helpers
    private void intersectElements(Node self, DynamicSet otherSet, DynamicSet intersection)
    {
        if (self == null)
        {
            return;
        }
        if (otherSet.isElement(self.element))
        {
            intersection.add(size);
        }
        intersectElements(self.left, otherSet, intersection);
        intersectElements(self.right, otherSet, intersection);
    }
    private void addAll(Node self, DynamicSet union)
    {
        if(self != null)
        {
            addAll(self.left, union);
            union.add(self.element);
            addAll(self.right, union);
        }
    }

    private Node insert(Node self, int element)
    {
        if (self == null)
        {
            return new Node(element);
        }

        if(element<self.element)
        {
            self.left = insert(self.left, element);
        }

        if(element>self.element)
        {
            self.right = insert(self.right, element);
        }

        return self;
    }

    private boolean searchNode(Node self, int element)
    {
        if(self == null)
        {
            return false;
        }
        if(self.element == element)
        {
            return true;
        }
        return element < self.element ? searchNode(self.left, element) : searchNode(self.right, element);
    }
    
    private Node deleteNode(Node self, int element)
    {
        if (self == null)
        {
            return null;
        }

        if (element < self.element)
        {
            self.left = deleteNode(self.left, element);

        }
        else if (element > self.element)
        {
            self.right = deleteNode(self, element);
        }
        else
        {
            if(self.left == null)
            {
                return self.right;
            }
            if(self.right == null)
            {
                return self.left;
            }
            self.element = findMin(self.right);
            self.right = deleteNode(self.right, self.element);
        }

        return self;
    }

    private void inorderTraversal(Node self, StringBuilder sb) {
        if (self != null) {
            inorderTraversal(self.left, sb);
            sb.append(self.element).append(" ");
            inorderTraversal(self.right, sb);
        }
    }

    private int findMin(Node self)
    {
        while (self.left != null)
        {
            self = self.left;
        }
        return self.element;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{ ");
        inorderTraversal(self, sb);  // Collect elements in sorted order
        sb.append("}");
        return sb.toString().trim();  // Remove any trailing spaces
    }

}

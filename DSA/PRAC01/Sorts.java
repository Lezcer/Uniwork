/** 
** Software Technology 152
** Class to hold various static sort methods.
*/
class Sorts
{
    // bubble sort
    public static void bubbleSort(int[] A)
    {
        boolean sorted;
        int pass = 0;
        do
        {
            sorted = true;
            //Assume array is sorted
            for(int i=0; i<(A.length - pass - 2); i++)
            //Pass reduces array size, the other '-1' is for the '+1' below
            {
                if(A[i] > A[i+1])
                {
                    int temp = A[i];
                    A[i] = A[i+1];
                    A[i+1] = temp;
                    sorted = false;
                }
            }
            pass = pass + 1;
            //Array size reduced
        }
        while(!sorted);
	
    }//bubbleSort()

    // selection sort
    public static void selectionSort(int[] A)
    {
	    for(int n=0; n<(A.length -1); n++)
	    {
	        int minIdx = n;
	        //set this as the new minimum
	        for(int j=n+1; j<(A.length-1); j++)
	        {
		        if(A[j]<A[minIdx])//checks if adjacent element is smaller
		        {
		            minIdx = j;
		            //The adjacent term is the new minimum
		        }//loops through the whole array to look for a smaller element
	        }   
	        int temp = A[minIdx];
	        A[minIdx] = A[n];
	        A[n] = temp;
	}
	
    }// selectionSort()

    // insertion sort
    public static void insertionSort(int[] A)
    {
        for(int n=1; n<(A.length-1); n++)
        //Start from element 0 as we will be going backwards
        {
            int i = n;
            int temp = A[i];
            while (i>0 && A[i-1] > temp)
            //Using > to keep the sort stable
            {
                A[i] = A[i-1];
                //This will keep it shuffling
                i = i-1;
            }
            A[i] = temp;
	    }
    }// insertionSort()

    // mergeSort - front-end for kick-starting the recursive algorithm
    public static void mergeSort(int[] A)
    {
    }//mergeSort()
    private static void mergeSortRecurse(int[] A, int leftIdx, int rightIdx)
    {
    }//mergeSortRecurse()
    private static void merge(int[] A, int leftIdx, int midIdx, int rightIdx)
    {
    }//merge()


    // quickSort - front-end for kick-starting the recursive algorithm
    public static void quickSort(int[] A)
    {
    }//quickSort()
    private static void quickSortRecurse(int[] A, int leftIdx, int rightIdx)
    {
    }//quickSortRecurse()
    private static int doPartitioning(int[] A, int leftIdx, int rightIdx, int pivotIdx)
    {
		return 0;	// TEMP - Replace this when you implement QuickSort
    }//doPartitioning


}//end Sorts calss

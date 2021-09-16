// Preni Amijanian
// Project 1 Task 1
import java.util.Random;
import java.util.Arrays;

public class tst
{
    public static void main(String[] args)
    {   // n is size of the array
        int n[] = {10000, 20000, 50000, 100000, 200000, 300000, 400000, 500000, 1000000, 5000000, 10000000, 50000000, 100000000}; 
        //int n[] = {10, 15, 20}; // n is size of the array
        int arr[]; // to send the unsorted array to the merge sort
        int secondArr[]; // to copy the first unsorted array arr[] and pass this unsorted array to the quick sort
        for(int arrLen: n)
        {
            arr = fillArr(arrLen); // call the function fillArr(n) to initialize the array with random numbers
            secondArr = Arrays.copyOf(arr, arrLen); // copy unsorted array arr to the secondArr
            // print unsorted array
            /*System.out.println("\nThe unsorted array before merge is: ");
            for(int i = 0; i < arr.length; i++) 
            {
                System.out.print(" " + arr[i] + " ");// print contents of unsorted array
            }*/
            final long mergeStart = System.currentTimeMillis(); // find the time before starting the merge sort function
            mergeSort(arr, 0, arrLen -1); 
            final long mergeEnd = System.currentTimeMillis(); // find the time after the array is sorted 
            final long mergeExecTime = mergeEnd - mergeStart; // subtract the time to get the time it took to sort the array 
            //for loop to print sorted elements
            /*System.out.println("\nThe sorted (merge) array is: ");
            for(int i = 0; i < arr.length; i++) 
            {
                System.out.print(" " + arr[i] + " ");// print contents of sorted array
            }*/
            /*System.out.println("\nThe unsorted array before quick is: ");
            for(int i = 0; i < secondArr.length; i++) 
            {
                System.out.print(" " + secondArr[i] + " ");// print contents of unsorted array
            }*/
            final long quickStart = System.currentTimeMillis(); // find the time before starting the quick sort function
            quickSort(secondArr, 0, arrLen -1);
            final long quickEnd = System.currentTimeMillis(); // find the time after the array is sorted 
            final long quickExecTime = quickEnd - quickStart; // subtract the time to get the time it took to sort the array
            //for loop to print sorted elements
            /*System.out.println("\nThe sorted (quick) array is: ");
            for(int i = 0; i < secondArr.length; i++) 
            {
                System.out.print(" " + secondArr[i] + " ");// print contents of sorted array
            }*/
            // print the time for each sort method
            System.out.println("\nThe execution time for mergeSort with n " + arrLen + " is: " + mergeExecTime);
            System.out.println("\nThe execution time for quickSort with n " + arrLen + " is: " + quickExecTime);
        }
    }
    public static int[] fillArr(int n)
    {
        int arr[] = new int[n]; // create an array and fill its elements with random numbers
        for(int i = 0; i < arr.length; i++)
        {
            //arr[i] = new Random().nextInt((100 - 1) + 1);
            arr[i] = new Random().nextInt((1000000 - 1) + 1); //random integers for the elements of array(+ integers only 1 to 1000000)
        }
        return arr;
    }
    public static void mergeSort(int[] arr, int low, int high)
    {
        int mid;
        if(low < high)
        {
            mid = low + (high - low)/2; // midle index
            mergeSort(arr, low, mid); // sort left side
            mergeSort(arr, mid+1, high); // sort right side 
            merge(arr, low, mid, high); // combine the two parts
        }
    }
    public static void merge(int[] arr, int low, int mid, int high)
    {
        int leftSize = mid - low + 1; // size of temporary left subarray
        int rightSize = high - mid; // size of temporary right subarray
        int tempLeft[] = new int[leftSize]; // create a temp array for left part
        int tempRight[] = new int[rightSize]; // create a temp array for right part
        // for loop to copy values of the left part of the arr array into tempLeft
        for(int i = 0; i < tempLeft.length; i++)
        {
            tempLeft[i] = arr[low+i]; // assign values of the left part of the arr (starting low+0) into tempLeft
        }
        // for loop to copy values of the right part of the arr array into tempRight
        for(int i = 0; i < tempRight.length; i++)
        {
            tempRight[i] = arr[mid+1+i]; // assign values of the right part of the arr (starting mid+1+0) into tempLeft
        }
        // initialize the two index
        int left = 0;
        int right = 0;
        for(int i = low; i < high+1; i++) // to copy the sorted left/right subarrays back to arr
        {
            if(left < tempLeft.length && right < tempRight.length) // if index left and right are both smaller than the temp array sizes
            {
                if(tempLeft[left] < tempRight[right])
                {   // if the smaller value is in the left subarray, copy the value into arr at index i
                    arr[i] = tempLeft[left];
                    left++; // increment the left index to point to the next small value in left subarray
                }
                else // tempLeft[left] > tempRight[right]
                {
                    // if the smaller value is in the right subarray, copy the value into arr at index i
                    arr[i] = tempRight[right];
                    right++; // increment the right index to point to the next small value in right subarray
                }
            }
            else if(left < tempLeft.length) 
            {   // the elements in the right subarray have all been copied into arr but there are still elements left in left subarray
                arr[i] = tempLeft[left];
                left++; // increment the left index to point to the next small value in left subarray
            }
            else // right < tempRight.length 
            {   // the elements in the left subarray have all been copied into arr but there are still elements left in right subarray
                arr[i] = tempRight[right];
                right++; // increment the right index to point to the next small value in right subarray
            }
        }
    }
    public static void quickSort(int[] arr, int low, int high)
    {
        int pivotPoint;
        if(low < high)
        {
            pivotPoint = partition(arr, low, high); // call the partition method
            quickSort(arr, low, pivotPoint-1); // sort the lower subarray up until the pivot point
            quickSort(arr, pivotPoint+1, high); // sort the upper subarray after the pivot point to high
        }
    }
    public static int partition(int[] arr, int low, int high)
    {
        int j;
        int pivotItem;
        pivotItem = arr[high]; //choose last element to be the pivot item
        j = low - 1; // assign index j to be low-1
        for(int i = low; i < high; i++) // i starts from low up until high
        {
            if(arr[i] <= pivotItem)
            {   // if pivotItem is larger than the element at index i 
                j++; // we first increment j by one
                int temp; // then we swap that element of arr[i] with element in arr[j]
                temp = arr[j];
                arr[j] = arr[i];
                arr[i] = temp;
            }
        }
        int tempVal; // swap the elements of arr[high] and arr[j+1] to make sure the pivot index is at correct position
        tempVal = arr[j+1];
        arr[j+1] = arr[high];
        arr[high] = tempVal;
        return j+1; // return the correct index of the pivot
    }
}


        
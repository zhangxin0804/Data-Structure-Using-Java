import java.util.Random;

public class MergeSortSolution {

	public static void main(String[] args) {
		Integer[] test = new Integer[10];
		Random rdm = new Random();
		for(int i=0; i<10;i++){
			test[i] = rdm.nextInt(10);
		}
		System.out.println("The original array is: ");
		for(int i=0; i<10;i++){
			System.out.print(test[i] + " ");
		}
		sort(test);
		System.out.println("\nThe sorted array is: ");
		for(int i=0; i<10;i++){
			System.out.print(test[i] + " ");
		}
	}

	/*
	 * This is the public interface for sorting.
	 */
	public static void sort(Comparable[] a){
		
		Comparable[] aux = new Comparable[a.length]; // watch out: you cannot put auxiliary array creation in recursive sort 
		sort(a,aux,0,a.length-1);                    // routine, it will cause more memory space and stack overflow.
	}
	/*
	 * This is the recursive sort routine.
	 */
	private static void sort(Comparable[] a, Comparable[] aux, int lo, int hi){
		
		int mid = lo + (hi-lo)/2;                 // get the middle position of array as the bound of the subarray
		if( hi<=lo )                              // end of dividing array into subarray
			return;
		sort(a, aux, lo, mid);                    // recursively sort the left part
		sort(a,aux,mid+1,hi);                     // recursively sort the right part
		merge(a,aux,lo,mid,hi);                   // merge subarray
	}
	
	private static void merge(Comparable[] a, Comparable[] aux, int lo, int mid, int hi){
		
		for(int k = lo; k <= hi; k++){            // watch out the lower bound and higher bound
			aux[k] = a[k];                        // auxiliary array to get data copy from the original array
		}
		int i = lo;                               // three pointers to maintain, the first one is the left part of subarray
		int j = mid + 1;                          // the second one is the right part of subarray
		for(int k = lo; k <= hi; k++){            // the third one is the original part of array
			
			if( i > mid )                          // when pointer i is exhausted, copy all right part of subarray to original
				a[k] = aux[j++];
			else if( j > hi)
				a[k] = aux[i++];                  // when pointer j is exhausted, copy all left part of subarray to original
			else if( less(aux[j],aux[i]) )
				a[k] = aux[j++];
			else                                  // watch out: when aux[i]<=aux[j], always put the left part of subarray first
				a[k] = aux[i++];                  // keep stable sorting
			
		}
	}
	
	private static boolean less(Comparable v, Comparable w){
		return v.compareTo(w)<0;
	}
}

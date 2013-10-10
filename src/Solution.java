public class Solution{
	public int solution(int[] A){
		
		if(A.length ==0 ) return -1;
		
		int b[] = new int[A.length];
		int max = Integer.MIN_VALUE;
		int min = Integer.MAX_VALUE;
		for(int i=0; i<A.length; i++){
			max = Math.max(max, A[i]);
			b[i] = max;
		}
		for(int i=A.length-1; i >=0; i--){
			min = Math.min(min, A[i]);
			if(min == b[i])
				return i;
		}
		return -1;
	}

	public static void main(String args[]){
		int[] A = {4,2,2,3,1,4,7,7,6,7};
		System.out.println(new Solution().solution(A));
		
		int[] B = {};
		System.out.println(new Solution().solution(B));
		
		int[] C = {1};
		System.out.println(new Solution().solution(C));

	}
}
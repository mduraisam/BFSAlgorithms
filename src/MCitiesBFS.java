import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;

public class MCitiesBFS {

	public static void main(String[] args) {
		int[] T = { 9, 1, 4, 9, 0, 4, 8, 9, 0, 1 };
		int[] result = solution(T);
		System.out.println(Arrays.toString(result));
	}

	public static int[] solution(int[] T) {
		int[] output = new int[T.length - 1];
		int capital = 0;
		LinkedList<Integer> edges[] = new LinkedList[T.length];
		try {
			for (int i = 0; i < edges.length; ++i) {
				edges[i] = new LinkedList<Integer>();
			}
			for (int i = 0; i < T.length; i++) {
				if (T[i] == i) {
					capital = i;
				} else {
					edges[i].add(T[i]);
					edges[T[i]].add(i);
				}
			}
			output = traverseCity(capital, edges, output);
		} catch (Exception exception) {
			exception.printStackTrace();
		}
		return output;
	}


	 static int[] traverseCity(int capital, LinkedList<Integer> edges[], int[] output) {
		 System.out.println("--edges--"+Arrays.asList(edges));
		boolean visitedCity[] = new boolean[edges.length];
		LinkedList<Integer> lst = new LinkedList<Integer>();
		int[] graphDepth = new int[output.length + 1];
		visitedCity[capital] = true;
		lst.add(capital);
		graphDepth[capital] = 0;
		while (!lst.isEmpty()) {
			int city = lst.poll();
			System.out.println("--city--"+city);
			Iterator<Integer> cityIter = edges[city].listIterator();
			while (cityIter.hasNext()) {
				int nextCity = cityIter.next();
				graphDepth[nextCity] = graphDepth[city] + 1;
				System.out.println("--inside nextCity--"+nextCity);
				System.out.println("--nextCity--"+nextCity);
				if (!visitedCity[nextCity]) {
					int resultIndex = graphDepth[nextCity] - 1;
					output[resultIndex] = output[resultIndex] + 1;
					lst.add(nextCity);
					visitedCity[nextCity] = true;
					System.out.println("--inside nextCity--"+nextCity);
				}
			}
		}
		System.out.println("--output--"+output);
		return output;
	}
}

import java.util.Iterator;
import java.util.LinkedList;

public class BFSSolution {

	public int[] solution(int[] T) {
		int[] result = new int[T.length - 1];
		Graph graph = new Graph(T.length);
		int capital = 0;
		for (int i = 0; i < T.length; i++) {
			if (T[i] == i) {
				capital = i;
			} else {
				graph.addEdge(i, T[i]);
				graph.addEdge(T[i], i);
			}
		}
		return graph.traverse(result, capital);
	}

	class Graph {
		private int root;
		private LinkedList<Integer> edges[];

		Graph(int vertex) {
			root = vertex;
			edges = new LinkedList[vertex];
			for (int i = 0; i < vertex; ++i)
				edges[i] = new LinkedList();
		}

		void addEdge(int source, int target) {
			edges[source].add(target);
		}

		int[] traverse(int[] result, int capital) {
			boolean visited[] = new boolean[root];
			LinkedList<Integer> queue = new LinkedList<Integer>();
			visited[capital] = true;
			queue.add(capital);
			int[] depthArr = new int[result.length + 1];
			depthArr[capital] = 0;
			while (queue.size() != 0) {
				int currentCity = queue.poll();
				Iterator<Integer> i = edges[currentCity].listIterator();
				while (i.hasNext()) {
					int adjCity = i.next();
					depthArr[adjCity] = depthArr[currentCity] + 1;
					if (!visited[adjCity]) {
						visited[adjCity] = true;
						int resultIndex = depthArr[adjCity] - 1;
						result[resultIndex] = result[resultIndex] + 1;
						queue.add(adjCity);
					}
				}
			}
			return result;
		}

	}

	public static void main(String[] args) {
		int[] inputArr = new int[10];
		inputArr[0] = 9;
		inputArr[1] = 1;
		inputArr[2] = 4;
		inputArr[3] = 9;
		inputArr[4] = 0;
		inputArr[5] = 4;
		inputArr[6] = 8;
		inputArr[7] = 9;
		inputArr[8] = 0;
		inputArr[9] = 1;
		int[] result = new BFSSolution().solution(inputArr);
		for (int i : result) {
			System.out.print(i + " ");
		}
	}
}

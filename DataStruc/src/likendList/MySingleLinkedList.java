package likendList;

/*
리스트
	기본적인 연산 : 삽입(insert) , 삭제 (remove), 검색 (search) 등 
	리스트를 구연하는 대표적인 두 가지 방법 : 배열, 연결 리스트
	
배열의 단점
	크기가 고정 - reallocation이 필요
	리스트의 중간에 원소를 삽입하거나 삭제할 경우 다수의 데이터를 옮겨야
	
연결리스트
	다른 데이터의 이동 없이 중간에 삽입이나 삭제가 가능하며, 길이의 제한이 없음
	but 랜덤 액세스가 불가능 (배열은 모든 칸의 크기가 같기 때문에 시작주소+칸의 크기*칸의 위치를 이용해 간단한 주소 위치 계산 가능함)

*/

public class MySingleLinkedList<T> {

	public Node<T> head;
	public int size;

	public MySingleLinkedList() {
		head = null;
		size = 0;
	}

	public void addFirst(T item) {
		// 새로운 노드를 만들고 추가할 데이터 저장
		Node<T> newNode = new Node<T>(item);
		// 새로운 노드의 next필드가 현재의 head노드를 가르키도록 한다.
		newNode.next = head;
		// 새로운 노드를 새로운 head노드로 한다.
		head = newNode;
		size++;
	}

	public void addAfter(Node<T> before, T item) {
		// 새로운 노드를 만들고 데이터 저장
		Node<T> temp = new Node<T>(item);
		// 새로운 노드의 next필드가 before의 다음노드를 가리키도록
		temp.next = before.next;
		// 새로운 노드를 before의 다음노드로
		before.next = temp;
		size++;

		// 장소 = 값;

		/*
		 * 단순연결리스트에 새로운 노드를 삽입 할 때 삽입할 위치의 바로 앞 노드의 주소가 필요하다. 즉 어떤 노드의 뒤에 삽입하는 것은 간단하지만
		 * 반대로 어떤 노드의 얖에 삽입하는 것은 간단하지 않다.
		 */

	}

	// 연결리스트 index번쨰 위치에 데이터 삽입
	public void add(int index, T item) {
		if (index < 0 || index > size) {
			return;
		}
		if (index == 0) {
			addFirst(item);
		} else {
			Node<T> q = getNode(index - 1);
			addAfter(q, item);
		}
	}

	public T removeFirst() {
		// head가 null이 아니라면 head가 현재 head노드의 다음 노드를 가리키게 만든다.
		if (head == null) {
			return null;
		} else {
			// return 안하고 대입해도 되지만 return 한다고 하면 기존 head가 날아가기 때문에 temp만들어서 담아야돼
			T temp = head.data;
			head = head.next;
			size--;
			return temp;
		}
	}

	public T removeAfter(Node<T> before) {
		// before의 다음 노드가 null이 아니라면 before의 next필드가 현재 next노드의 다음노드를 기리키게 만든다.
		if (before.next == null) {
			return null;
		} else {
			T temp = before.next.data;
			before.next = before.next.next;
			size--;
			return temp;
		}

		/*
		 * 단순연결리스트(singleLinkedList)에서는 어떤 노드를 삭제할 때는 삭제할 노드의 바로 앞 노드의 주소가 필요하다. 삭제할 노드의
		 * 주소만으로는 삭제하기 힘들다. (할수는 있음)
		 */
	}

	// delete
	public T remove(int index) {
		if (index < 0 || index >= size) {
			return null;
		}
		
		if(index == 0 ) {
			return removeFirst();
		}
		
		Node<T> prev = getNode(index - 1);
		return removeAfter(prev);

	}

	public Node<T> getNode(int index) {
		if (index < 0 || index >= size) {
			return null;
		}

		Node<T> p = head;
		for (int i = 0; i < index; i++) {
			p = p.next;
		}
		return p;

	}

	public T get(int index) {
		if (index < 0 || index >= size) {
			return null;
		}

//		Node<T> p = head;
//		for(int i = 0; i<index;i++) {
//			p=p.next;
//		}
//		return p.data;

		return getNode(index).data;
	}

	// search
	public int indexOf(T item) {
		Node<T> p = head;
		int index = 0;
		while (p != null) {
			if (p.data.equals(item))
				return index;

			p = p.next;
			index++;
		}
		return -1;
	}
	/*
	 * 연결리스트의 노드들을 처음부터 순서대로 방문하는 것을 순회(traverse)한다고 말한다. indexOf 메서드는 입력된 데이터 item과
	 * 동일한 데이터를 저장한 노드를 찾아서 그 노드번호(index)를 반환한다. 그것을 위해서 연결리스트를 순회한다.
	 */

	public static void main(String[] args) {
		MySingleLinkedList<String> list = new MySingleLinkedList<>();

		list.add(0, "Saturday");
		list.add(1, "Friday");
		list.add(0, "Monday");
		list.add(2, "Tuesday"); // M, S, T, F

		String str = list.get(2); // str = "Tuesday";
		list.remove(2); // M, S, F
		int pos = list.indexOf("Friday"); // pos = 2
	}
}

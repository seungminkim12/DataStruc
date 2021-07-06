package likendList;

/*
����Ʈ
	�⺻���� ���� : ����(insert) , ���� (remove), �˻� (search) �� 
	����Ʈ�� �����ϴ� ��ǥ���� �� ���� ��� : �迭, ���� ����Ʈ
	
�迭�� ����
	ũ�Ⱑ ���� - reallocation�� �ʿ�
	����Ʈ�� �߰��� ���Ҹ� �����ϰų� ������ ��� �ټ��� �����͸� �Űܾ�
	
���Ḯ��Ʈ
	�ٸ� �������� �̵� ���� �߰��� �����̳� ������ �����ϸ�, ������ ������ ����
	but ���� �׼����� �Ұ��� (�迭�� ��� ĭ�� ũ�Ⱑ ���� ������ �����ּ�+ĭ�� ũ��*ĭ�� ��ġ�� �̿��� ������ �ּ� ��ġ ��� ������)

*/

public class MySingleLinkedList<T> {

	public Node<T> head;
	public int size;

	public MySingleLinkedList() {
		head = null;
		size = 0;
	}

	public void addFirst(T item) {
		// ���ο� ��带 ����� �߰��� ������ ����
		Node<T> newNode = new Node<T>(item);
		// ���ο� ����� next�ʵ尡 ������ head��带 ����Ű���� �Ѵ�.
		newNode.next = head;
		// ���ο� ��带 ���ο� head���� �Ѵ�.
		head = newNode;
		size++;
	}

	public void addAfter(Node<T> before, T item) {
		// ���ο� ��带 ����� ������ ����
		Node<T> temp = new Node<T>(item);
		// ���ο� ����� next�ʵ尡 before�� ������带 ����Ű����
		temp.next = before.next;
		// ���ο� ��带 before�� ��������
		before.next = temp;
		size++;

		// ��� = ��;

		/*
		 * �ܼ����Ḯ��Ʈ�� ���ο� ��带 ���� �� �� ������ ��ġ�� �ٷ� �� ����� �ּҰ� �ʿ��ϴ�. �� � ����� �ڿ� �����ϴ� ���� ����������
		 * �ݴ�� � ����� �A�� �����ϴ� ���� �������� �ʴ�.
		 */

	}

	// ���Ḯ��Ʈ index���� ��ġ�� ������ ����
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
		// head�� null�� �ƴ϶�� head�� ���� head����� ���� ��带 ����Ű�� �����.
		if (head == null) {
			return null;
		} else {
			// return ���ϰ� �����ص� ������ return �Ѵٰ� �ϸ� ���� head�� ���ư��� ������ temp���� ��ƾߵ�
			T temp = head.data;
			head = head.next;
			size--;
			return temp;
		}
	}

	public T removeAfter(Node<T> before) {
		// before�� ���� ��尡 null�� �ƴ϶�� before�� next�ʵ尡 ���� next����� ������带 �⸮Ű�� �����.
		if (before.next == null) {
			return null;
		} else {
			T temp = before.next.data;
			before.next = before.next.next;
			size--;
			return temp;
		}

		/*
		 * �ܼ����Ḯ��Ʈ(singleLinkedList)������ � ��带 ������ ���� ������ ����� �ٷ� �� ����� �ּҰ� �ʿ��ϴ�. ������ �����
		 * �ּҸ����δ� �����ϱ� �����. (�Ҽ��� ����)
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
	 * ���Ḯ��Ʈ�� ������ ó������ ������� �湮�ϴ� ���� ��ȸ(traverse)�Ѵٰ� ���Ѵ�. indexOf �޼���� �Էµ� ������ item��
	 * ������ �����͸� ������ ��带 ã�Ƽ� �� ����ȣ(index)�� ��ȯ�Ѵ�. �װ��� ���ؼ� ���Ḯ��Ʈ�� ��ȸ�Ѵ�.
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

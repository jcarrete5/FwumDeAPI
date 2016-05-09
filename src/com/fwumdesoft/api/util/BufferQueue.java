package com.fwumdesoft.api.util;

import java.util.LinkedList;

/**
 * A Queue that has an internal buffer.<br>
 * When the Queue's length is equal to the buffer, then<br>
 * any subsequent "pushes" to the queue will remove the first element.
 * @author Jason Carrete
 */
public class BufferQueue<T> {
	private LinkedList<T> queue;
	private int bufferSize;
	
	public BufferQueue(int bufferSize) {
		queue = new LinkedList<T>();
		this.bufferSize = bufferSize;
	}
	
	/**
	 * Adds a new elements to the end of the queue.
	 * @return The element that was removed if the queue was already full, otherwise <tt>null</tt>;
	 */
	public T push(T e) {
		T result = null;
		if(queue.size() == bufferSize)
			result = queue.pop();
		queue.push(e);
		return result;
	}
	
	/**
	 * @see java.util.LinkedList#pop()
	 */
	public T pop() {
		return queue.pop();
	}
	
	/**
	 * @see java.util.LinkedList#peekFirst()
	 */
	public T peekFirst() {
		return queue.peekFirst();
	}
	
	/**
	 * @see java.util.LinkedList#peekLast()
	 */
	public T peekLast() {
		return queue.peekLast();
	}
	
	/**
	 * @see java.util.LinkedList#clear()
	 */
	public void clear() {
		queue.clear();
	}
	
	/**
	 * @return The size of the queue.
	 */
	public int size() {
		return queue.size();
	}
	
	/**
	 * Sets the bufferSize of this Queue if the newSize >= size().
	 * @param newSize New size of the buffer.
	 * @return <tt>true</tt> if the size changed, otherwise <tt>false</tt>.
	 */
	public boolean setBufferSize(int newSize) {
		boolean result = newSize >= size();
		if(result)
			bufferSize = newSize;
		return result;
	}
	
	/**
	 * Removes elements starting from the first until the queue is the same length as the buffer.
	 * @param newBufferSize new buffer size.
	 * @return A list of removed elements.
	 */
	public LinkedList<T> trim(int newBufferSize) {
		LinkedList<T> removed = new LinkedList<>();
		for(int i = 0; i < bufferSize - newBufferSize && bufferSize - newBufferSize >= 0; i++)
			removed.push(queue.pop());
		bufferSize = newBufferSize;
		return removed;
	}
	
	/**
	 * @return The size of the buffer.
	 */
	public int bufferSize() {
		return bufferSize();
	}
}

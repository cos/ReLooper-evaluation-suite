package extra166y;

import extra166y.Ops.IntToObject;

public class ParallelArray<T> {
	T e0;
	T e1;
	private Object[] innerArray;

	public ParallelArray() {
	}

	public static jsr166y.ForkJoinPool defaultExecutor() {
		return null;
	}

	public static <T> ParallelArray<T> createUsingHandoff(T[] source, jsr166y.ForkJoinPool executor) {
		ParallelArray<T> parallelArray = new ParallelArray<T>();
		parallelArray.innerArray = source;
		return parallelArray;
	}

	public void apply(final Ops.Procedure<? super T> procedure) {
		Runnable r1 = new Runnable() {
			@Override
			public void run() {
				procedure.op(e0);
			}
		};
		Runnable r2 = new Runnable() {
			@Override
			public void run() {
				procedure.op(e1);
			}
		};
		Thread t1 = new Thread(r1);
		Thread t2 = new Thread(r2);
		t1.start();
		t2.start();
		try {
			t1.join();
			t2.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void applySeq(Ops.Procedure<? super T> procedure) {
		procedure.op(e0);
		procedure.op(e1);
	}

	@SuppressWarnings("unchecked")
	public void replaceWithGeneratedValue(final Ops.Generator<? super T> generator) {
		Runnable r1 = new Runnable() {
			@Override
			public void run() {
				e0 = (T) generator.op();
			}
		};
		Runnable r2 = new Runnable() {
			@Override
			public void run() {
				e1 = (T) generator.op();
			}
		};
		Thread t1 = new Thread(r1);
		Thread t2 = new Thread(r2);
		t1.start();
		t2.start();
		try {
			t1.join();
			t2.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		innerArray[0] = e0;
		innerArray[1] = e1;
	}

	@SuppressWarnings("unchecked")
	public void replaceWithGeneratedValueSeq(Ops.Generator<? super T> generator) {
		e0 = (T) generator.op();
		e1 = (T) generator.op();
		
		innerArray[0] = e0;
		innerArray[1] = e1;
	}

	public T[] getArray() {
		return (T[]) innerArray;
	}

	@SuppressWarnings("unchecked")
	public void replaceWithMappedIndex(IntToObject<? super T> intToObject) {
		e0 = (T) intToObject.op(0);
		e1 = (T) intToObject.op(1);
		
		innerArray[0] = e0;
		innerArray[1] = e1;
	}

	public void replaceWithMappedIndexSeq(IntToObject<? super T> intToObject)  {
		e0 = (T) intToObject.op(0);
		e1 = (T) intToObject.op(1);
		
		innerArray[0] = e0;
		innerArray[1] = e1;
	}
}

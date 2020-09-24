package skyric.mineInstant.container;

import java.util.function.IntConsumer;
import java.util.function.IntSupplier;

import net.minecraft.util.IntReferenceHolder;

public class ContainerIntReferenceHolder extends IntReferenceHolder{
	
	private final IntSupplier getter;
	private final IntConsumer setter;
	
	public ContainerIntReferenceHolder(IntSupplier getter, IntConsumer setter) {
		this.getter = getter;
		this.setter = setter;
	}

	@Override
	public int get() {
		return getter.getAsInt();
	}

	@Override
	public void set(int value) {
		this.setter.accept(value);
	}

}

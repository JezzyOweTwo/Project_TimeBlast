package time_blast.file_reading;

public interface FileReadable<T>{
	public <J> T create(J value);

}

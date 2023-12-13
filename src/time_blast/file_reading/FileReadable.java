package time_blast.file_reading;

public interface FileReadable<T>{
	public <I,J> T create(I key ,J value);
}

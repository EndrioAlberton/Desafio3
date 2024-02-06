package db;
public class DbException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public DbException(String msg) {
		super(msg);
	}

    public DbException(String string, int i) {
        //TODO Auto-generated constructor stub
    }
}

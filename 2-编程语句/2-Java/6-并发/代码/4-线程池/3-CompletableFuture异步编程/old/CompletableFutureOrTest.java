package pool;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

public class CompletableFutureOrTest {

	public static void main(String[] args) {
		CompletableFuture<String> f1 = CompletableFuture.supplyAsync(() -> {
			int t =3;
			System.out.println("f1 -> "+t);
			
			sleep(t, TimeUnit.SECONDS);
			return String.valueOf(t);
		});

		CompletableFuture<String> f2 = CompletableFuture.supplyAsync(() -> {
			int t =1;
			System.out.println("f2 -> "+t);
			
			sleep(t, TimeUnit.SECONDS);
			return String.valueOf(t);
		});

		CompletableFuture<String> f3 = f1.applyToEither(f2, s -> s);

		System.out.println("f3-->"+f3.join());
	}
	
	
	public static void sleep(int t, TimeUnit tu) {
		try {
			tu.sleep(t);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}

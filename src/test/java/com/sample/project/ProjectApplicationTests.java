package com.sample.project;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ProjectApplicationTests {

	@Test
	void contextLoads() {
	}


	public static void main(String[] args) {

//		List<Shop> shops = List.of(
//			new Shop("BestPrice",1.0),
//			new Shop("LetsSaveBig",2.0),
//			new Shop("MyFavoriteShop", 3.0),
//			new Shop("BuyItAll", 4.0));
//
//		long start = System.nanoTime();
//
//		completeFutureFindShops2(shops);
//
//		long duration = (System.nanoTime() - start) / 1_000_000;
//
//		System.out.println(duration);
//		System.out.println(duration);
//		System.out.println(duration);

		List<String> list1 = new java.util.ArrayList<>(List.of("abc", "bbc", "def"));
		List<String> list2 = new java.util.ArrayList<>(List.of("abc", "bbc", "rry"));

		list1.addAll(list2);

		List<String> collect = list1.stream().map(list -> {
			if (list2.stream().noneMatch(list::equals)) {
				return list;
			}
			return "";
		}).collect(Collectors.toList());
		System.out.println("collect = " + collect);

	}

	private static void findShops(List<Shop> shops) {
		List<String> findShops = shops.stream()
			.map(shop -> {
				try {
					TimeUnit.SECONDS.sleep(1);
				} catch (InterruptedException e) {
					throw new RuntimeException(e);
				}
				return String.format("%s price is %.2f", shop.getName(), shop.getPrice());
			})
			.collect(Collectors.toList());
		findShops.forEach(System.out::println);
	}

	private static void parallelFindShops(List<Shop> shops) {
		shops.parallelStream()
			.map(shop -> {
				try {
					System.out.println(
						"Thread.currentThread().getName() = " + Thread.currentThread().getName());

					TimeUnit.SECONDS.sleep(1);
				} catch (InterruptedException e) {
					throw new RuntimeException(e);
				}
				return String.format("%s price is %.2f", shop.getName(), shop.getPrice());
			})
			.collect(Collectors.toList());
	}

	private static void completeFutureFindShops(List<Shop> shops) {
		shops.stream()
			.map(shop -> CompletableFuture.supplyAsync(() -> String.format("%s price is %.2f", shop.getName(), shop.getPrice()))).collect(Collectors.toList());
	}


	private static List<String> completeFutureFindShops2(List<Shop> shops) {

		ExecutorService executorService = Executors.newFixedThreadPool(100);

		List<CompletableFuture<String>> collect = shops.stream()
			.map(shop -> CompletableFuture.supplyAsync(

				() -> {
					try {
						TimeUnit.SECONDS.sleep(1);
					} catch (InterruptedException e) {
						throw new RuntimeException(e);
					}
					return String.format("%s price is %.2f", shop.getName(), shop.getPrice());
				},executorService))
			.collect(Collectors.toList());

		return
			collect.stream()
				.map(CompletableFuture::join)
				.collect(Collectors.toList());
	}



	static class Shop {


		private String name;
		private Double price;

		public Shop(String name, Double price) {
			this.name = name;
			this.price = price;
		}

		public String getName() {
			return name;
		}

		public Double getPrice() {
			return price;
		}
	}

}

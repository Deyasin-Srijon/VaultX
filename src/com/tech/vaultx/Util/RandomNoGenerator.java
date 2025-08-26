package com.tech.vaultx.Util;

import java.util.Random;

public class RandomNoGenerator {
	public static String randomNoGenerator(int num) {
		Random random = new Random();

        // Ensure 10 digits (no leading 0) → first digit 1-9, rest can be 0-9
        StringBuilder sb = new StringBuilder();
        sb.append(random.nextInt(9) + 1); // first digit (1–9)

        for (int i = 0; i < num-1; i++) {
            sb.append(random.nextInt(10)); // remaining digits (0–9)
        }

        return sb.toString();
	}
}

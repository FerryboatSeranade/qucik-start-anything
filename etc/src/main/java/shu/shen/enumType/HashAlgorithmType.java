package shu.shen.enumType;

import lombok.Getter;
@Getter
public enum HashAlgorithmType {
    /**
     * SHA-256算法
     */
    SHA256("SHA-256", "Secure Hash Algorithm");

    private final String algorithm;

    private final String desc;

    HashAlgorithmType(String algorithm, String desc) {
        this.algorithm = algorithm;
        this.desc = desc;
    }

}
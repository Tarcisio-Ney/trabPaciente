import java.time.LocalDate;
import java.time.Period;

public class Paciente {
    private String nome;
    private String sobrenome;
    private char sexo;
    private LocalDate nascimento;
    private byte idade;
    private short altura;  // em centímetros
    private double peso;
    private String cpf;
    private double imc;

    public Paciente(String nome, String sobrenome, char sexo, LocalDate nascimento, short altura, double peso, String cpf) {
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.sexo = sexo;
        this.nascimento = nascimento;
        this.altura = altura;
        this.peso = peso;
        this.cpf = cpf;
        this.idade = calcularIdade();
        this.imc = calcularIMC();
    }

    public float getIMC() {
        return (float) this.imc;
    }

    public double obterPesoIdeal() {
        double alturaMetros = altura / 100.0;
        if (sexo == 'M') {
            return (72.7 * alturaMetros) - 58;
        } else {
            return (62.1 * alturaMetros) - 44.7;
        }
    }

    public String obterCpfOfuscado() {
        return "***." + cpf.substring(3, 6) + ".***-**";
    }

    public String obterSituacaoIMC() {
        if (imc < 17) {
            return "Muito abaixo do peso";
        } else if (imc >= 17 && imc < 18.5) {
            return "Abaixo do peso";
        } else if (imc >= 18.5 && imc < 25) {
            return "Peso normal";
        } else if (imc >= 25 && imc < 30) {
            return "Acima do peso";
        } else if (imc >= 30 && imc < 35) {
            return "Obesidade I";
        } else if (imc >= 35 && imc < 40) {
            return "Obesidade II (severa)";
        } else {
            return "Obesidade III (mórbida)";
        }
    }

    private double calcularIMC() {
        return peso / Math.pow(altura / 100.0, 2);  // altura é convertida para metros
    }

    private byte calcularIdade() {
        return (byte) Period.between(nascimento, LocalDate.now()).getYears();
    }

    public boolean validarCPF() {
        if (cpf == null || cpf.length() != 11) {
            return false;
        }
        
        try {
            Long.parseLong(cpf);
        } catch (NumberFormatException e) {
            return false;
        }

        int[] cpfArray = new int[9];
        for (int i = 0; i < 9; i++) {
            cpfArray[i] = Character.getNumericValue(cpf.charAt(i));
        }

        int V1 = 0, V2 = 0;
        for (int i = 0; i < 9; i++) {
            V1 += cpfArray[i] * (9 - (i % 10));
            V2 += cpfArray[i] * (9 - ((i + 1) % 10));
        }

        V1 = (V1 % 11) % 10;
        V2 = V2 + V1 * 9;
        V2 = (V2 % 11) % 10;

        return V1 == Character.getNumericValue(cpf.charAt(9)) && V2 == Character.getNumericValue(cpf.charAt(10));
    }

    // Getters e Setters

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public char getSexo() {
        return sexo;
    }

    public void setSexo(char sexo) {
        this.sexo = sexo;
    }

    public LocalDate getNascimento() {
        return nascimento;
    }

    public void setNascimento(LocalDate nascimento) {
        this.nascimento = nascimento;
        this.idade = calcularIdade();
    }

    public byte getIdade() {
        return idade;
    }

    public void setIdade(byte idade) {
        this.idade = idade;
    }

    public short getAltura() {
        return altura;
    }

    public void setAltura(short altura) {
        this.altura = altura;
        this.imc = calcularIMC();
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
        this.imc = calcularIMC();
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public double getImc() {
        return imc;
    }

    public void setImc(double imc) {
        this.imc = imc;
    }
}
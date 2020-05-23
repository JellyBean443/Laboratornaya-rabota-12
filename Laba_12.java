public class Laba_12 {

    static final int size = 10000000;
    static final int h = size / 2;

    public void Metod1() {
        long a = System.currentTimeMillis();
        float[] arr = new float[size];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = 1;
        }

        for (int i = 0; i < arr.length; i++) {
            arr[i] = (float) (arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
        }
        System.out.println("Время работы первого метода = " + (System.currentTimeMillis() - a) + " миллисекунд");
    }

    public void Metod2() {
        long a = System.currentTimeMillis();
        float[] arr = new float[size];
        for (int i = 0; i < arr.length; i++) arr[i] = 1;

        float[] a1 = new float[h];
        float[] a2 = new float[h];
        System.arraycopy(arr, 0, a1, 0, h);
        System.arraycopy(arr, h, a2, 0, h);

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < a1.length; i++)
                    a1[i] = (float) (a1[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
            }
        });
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < a2.length; i++)
                    a2[i] = (float) (a2[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
            }
        });

        thread.start();
        thread1.start();
        try {
            thread.join();
            thread1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.arraycopy(a1, 0, arr, 0, h);
        System.arraycopy(a2, 0, arr, h, h);
        System.out.println("Время работы второго метода = " + (System.currentTimeMillis() - a) + " миллисекунд");
    }

    public static void main(String[] args) {
        Laba_12 laba_12 = new Laba_12();
        System.out.println("Первый метод:");
        laba_12.Metod1();
        System.out.println("Второй метод:");
        laba_12.Metod2();
    }
}
import java.util.Scanner;

public class App {

    class PointErrorRangeException extends Exception {
        String message = "PointErrorRangeException";

        public PointErrorRangeException(String message) {
            this.message = message;
        }

        public PointErrorRangeException() {

        }

        @Override
        public String getMessage() {
            return this.message;
        }
    }

    private int midtermWeight;
    private int midtermScoreEarned;
    private int midtermShifted;
    private int midtermShiftedAmount;
    private int midtermTotalPoint;
    private double midtermWeightedScore;

    private int finalWeight;
    private int finalScoreEarned;
    private int finalShifted;
    private int finalShiftedAmount;
    private int finalTotalPoint;
    private double finalWeightedScore;

    private int homeWorkWeight;
    private int homeWorkTotalPoint;
    private double homeWorkWeightedScore;
    private int numberOfAssigments;
    private int numberOfAtend;


    private Scanner scanner;
    private int totalScoreAssigments;
    private int maxTotalPointAssigments;

    

    public static void main(String[] args) throws Exception {

        App app = new App();
        app.scanner = new Scanner(System.in);
        

        app.run();
    }


    public void run() {
        try {
            begin();
            inputMidterm();
            inputFinal();
            inputHomeWork();
            
            validate();
            report();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    private void validate() throws App.PointErrorRangeException{
        if(this.midtermWeight + this.finalWeight + this.homeWorkWeight != 100 ){
            throw new PointErrorRangeException("trọng số của 3 phần điểm thi phải có tổng chính xác là 100. Nhỏ hơn hoặc lớn hơn 100 đều không được");
        }
    }

    public void begin(){
        System.out.println("This program reads exam/homework scores");
        System.out.println("and reports your overall course grade.");
        System.out.println();
    }

    public void report(){

        double weightedMidetermScore = 1.0 * this.midtermWeight * this.getMidtermTotalPoint() / 100;
        double weightedFinalScore = 1.0 * this.finalWeight * this.getFinalTotalPoint() / 100;
        double weightedHomeworkScore = 1.0 * this.homeWorkWeight * this.getHomeWorkTotalPoint()/ (this.getMaxHomeWorkTotalPoint());
        System.out.println("weightedHomeworkScore" + weightedMidetermScore);
        System.out.println(weightedFinalScore);
        System.out.println(weightedHomeworkScore);

        double grade = weightedMidetermScore + weightedFinalScore + weightedHomeworkScore;
        System.out.println(String.format("Overall percentage = %.1f", grade));

        double GPA = 0.0;

        if(grade >= 85){
            GPA = 3.0;
        }else if(grade >= 75){
            GPA = 2.0; 
        }else if(grade >= 60){
            GPA = 0.7;
        }else{
            GPA = 0;
        }
        System.out.println(String.format("Your grade will be at least: %.1f", GPA));

    }

    public int inputWeight() throws App.PointErrorRangeException {
        System.out.print("Wieght  (0-100)? ");
        int weight = scanner.nextInt();
        if (weight < 0 || weight > 100) {
            throw new PointErrorRangeException();
        }

        return weight;
    }

    public int inputScore() {
        System.out.print("Score earned? ");
        return scanner.nextInt();
    }

    public int inputShifted() {
        System.out.print("Were points shifted (1=yes, 2=no)? ");
        return scanner.nextInt();
    }

    public void inputMidterm() throws App.PointErrorRangeException {
        System.out.println();
        System.out.print("Midterm:");
        this.midtermWeight = inputWeight();
        this.midtermScoreEarned = inputScore();
        
        // shifted core
        int midtermShifted = inputShifted();
        if (midtermShifted == 1) {
            this.midtermShiftedAmount = scanner.nextInt();
        } else {
            this.midtermShiftedAmount = 0;
        }

        int totalPoint = getMidtermTotalPoint();

        double weightedScore = (1.0 * this.midtermScoreEarned / 100) * this.midtermWeight;

        System.out.println(String.format("Total points = %d / % d", totalPoint, 100));
        System.out.println(String.format("Weighted score = %.1f / %d", weightedScore, this.midtermWeight));
    }

    public void inputFinal() throws App.PointErrorRangeException {
        System.out.println();
        System.out.print("Final:");
        this.finalWeight = inputWeight();
        this.finalScoreEarned = inputScore();
        
        // shifted core
        int finalShifted = inputShifted();
        if (finalShifted == 1) {
            System.out.print("Shift amount? ");
            this.finalShiftedAmount = scanner.nextInt();
        } else {
            this.finalShiftedAmount = 0;
        }

        int totalPoint = getFinalTotalPoint();

        double weightedScore = (1.0 * totalPoint / 100) * this.finalWeight;

        System.out.println(String.format("Total points = %d / % d", totalPoint, 100));
        System.out.println(String.format("Weighted score = %.1f / %d", weightedScore, this.finalWeight));
    }

    

    public void inputHomeWork() throws App.PointErrorRangeException {
        System.out.println();
        System.out.println("Homework:");
        this.homeWorkWeight = inputWeight();
        System.out.print("Number of assigments? ");
        this.numberOfAssigments = scanner.nextInt();
        
        for (int i = 0; i < numberOfAssigments; i++) {
            System.out.print("Assignment " + i + " score and max? ");
            int score = scanner.nextInt();
            int max = scanner.nextInt();
            this.totalScoreAssigments += score;
            this.maxTotalPointAssigments += max;
        }

        // Điểm tối đa của phần Assignment là 150, nếu vượt quá thì cũng chỉ tính là 150 điểm.
        if(this.totalScoreAssigments > 150){
            this.totalScoreAssigments = 150;
        }
        if(this.maxTotalPointAssigments > 150){
            this.maxTotalPointAssigments = 150;
        }

        System.out.print("How many sections did you attend? ");
        this.numberOfAtend = scanner.nextInt();
        
        // point 
        int totalPoint = getHomeWorkTotalPoint();
        int maxTotalPoint = getMaxHomeWorkTotalPoint();


        // weight score
        double weightedScore = 1.0 * totalPoint * this.homeWorkWeight/maxTotalPoint;

        // 30 is Max Section point, 5 point per 1 attended.
        System.out.println(String.format("Section points = %d / %d", this.numberOfAtend * 5, 30));
        System.out.println(String.format("Total points = %d / %d", totalPoint, maxTotalPoint));
        System.out.println(String.format("Weighted score = %.1f / %d", weightedScore, this.homeWorkWeight));
    }


    private int getMaxHomeWorkTotalPoint() {
        return this.maxTotalPointAssigments + 30;
    }

    private int getSectionPoint(){
        int attend = this.numberOfAtend * 5;
        if(attend > 30){
            attend = 30;
        }
        return attend;
    }


    private int getHomeWorkTotalPoint() {
        
        return this.totalScoreAssigments + getSectionPoint();
    }

    private int getMidtermTotalPoint(){
        int totalPoint = this.midtermScoreEarned + this.midtermShiftedAmount;
        if (totalPoint > 100) {
            totalPoint = 100;
        }

        return totalPoint;
    }

    private int getFinalTotalPoint() {
        int totalPoint = this.finalScoreEarned + this.finalShiftedAmount;
        if (totalPoint > 100) {
            totalPoint = 100;
        }
        return totalPoint;
    }
}

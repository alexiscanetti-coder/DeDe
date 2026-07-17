public class Main
{
    public static void main(String[] args)
    {
        Warrior w = new Warrior();
        Mage m = new Mage();

        System.out.print(w.heroName+" - ");
        System.out.print(w.maxHealth+" - ");
        System.out.print(w.attackStrength);

        System.out.println("");

        System.out.print(m.heroName+" - ");
        System.out.print(m.maxHealth+" - ");
        System.out.print(m.attackStrength);
        }
}

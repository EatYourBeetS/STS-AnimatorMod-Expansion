package eatyourbeets.utilities;

import eatyourbeets.interfaces.delegates.FuncT1;

import java.util.*;
import java.util.function.Predicate;

public class BetaJUtils extends JUtils
{
    public static final Random RNG = JUtils.RNG;

    private static final StringBuilder sb1 = new StringBuilder();
    private static final StringBuilder sb2 = new StringBuilder();
    private static final ArrayList<String> classNames = new ArrayList<>();

    public static <T> ArrayList<T> Filter(T[] array, Predicate<T> predicate)
    {
        final ArrayList<T> res = new ArrayList<>();
        for (T t : array)
        {
            if (predicate.test(t))
            {
                res.add(t);
            }
        }

        return res;
    }

    public static <T> T Find(T[] array, Predicate<T> predicate)
    {
        for (T t : array)
        {
            if (predicate.test(t))
            {
                return t;
            }
        }

        return null;
    }

    public static <T, N extends Comparable<N>> T FindMax(T[] list, FuncT1<N, T> getProperty)
    {
        N best = null;
        T result = null;
        for (T t : list)
        {
            N prop = getProperty.Invoke(t);
            if (best == null || prop.compareTo(best) > 0)
            {
                best = prop;
                result = t;
            }
        }

        return result;
    }

    public static <T, N extends Comparable<N>> T FindMin(T[] list, FuncT1<N, T> getProperty)
    {
        N best = null;
        T result = null;
        for (T t : list)
        {
            N prop = getProperty.Invoke(t);
            if (best == null || prop.compareTo(best) < 0)
            {
                best = prop;
                result = t;
            }
        }

        return result;
    }

    public static <T> String InvokeBuilder(StringBuilder stringBuilder)
    {
        String result = stringBuilder.toString();
        stringBuilder.setLength(0);
        return result;
    }

    public static <T, N> ArrayList<N> Map(List<T> list, FuncT1<N, T> predicate)
    {
        final ArrayList<N> res = new ArrayList<>();
        for (T t : list)
        {
            res.add(predicate.Invoke(t));
        }

        return res;
    }

    public static <T, N extends Comparable<N>> N Max(T[] list, FuncT1<N, T> getProperty)
    {
        N best = null;
        for (T t : list)
        {
            N prop = getProperty.Invoke(t);
            if (best == null || prop.compareTo(best) > 0)
            {
                best = prop;
            }
        }

        return best;
    }

    public static <T, N extends Comparable<N>> N Max(Iterable<T> list, FuncT1<N, T> getProperty)
    {
        N best = null;
        for (T t : list)
        {
            N prop = getProperty.Invoke(t);
            if (best == null || prop.compareTo(best) > 0)
            {
                best = prop;
            }
        }

        return best;
    }

    public static <T> float Mean(List<T> list, FuncT1<Float, T> predicate)
    {
        if (list.size() <= 0)
        {
            return 0;
        }
        return Sum(list, predicate) / list.size();
    }

    public static <T> float Sum(Iterable<T> list, FuncT1<Float, T> predicate)
    {
        float sum = 0;
        for (T t : list)
        {
            sum += predicate.Invoke(t);
        }
        return sum;
    }

    public static <T, N extends Comparable<N>> N Min(T[] list, FuncT1<N, T> getProperty)
    {
        N best = null;
        for (T t : list)
        {
            N prop = getProperty.Invoke(t);
            if (best == null || prop.compareTo(best) < 0)
            {
                best = prop;
            }
        }

        return best;
    }

    public static <T, N extends Comparable<N>> N Min(Iterable<T> list, FuncT1<N, T> getProperty)
    {
        N best = null;
        for (T t : list)
        {
            N prop = getProperty.Invoke(t);
            if (best == null || prop.compareTo(best) < 0)
            {
                best = prop;
            }
        }

        return best;
    }

    public static <T> T Random(T[] items)
    {
        return items != null && items.length > 0 ? items[RNG.nextInt(items.length)] : null;
    }

    public static <T> T Random(ArrayList<T> items)
    {
        int size = items != null ? items.size() : 0;
        if (size == 0)
        {
            return null;
        }
        return items.get(RNG.nextInt(size));
    }

    public static <T> T Random(Collection<T> items)
    {
        int size = items != null ? items.size() : 0;
        if (size == 0)
        {
            return null;
        }

        int i = 0;
        int targetIndex = RNG.nextInt(size);
        for (T item : items)
        {
            if (i++ == targetIndex)
            {
                return item;
            }
        }

        throw new RuntimeException("items.size() was smaller than " + targetIndex + ".");
    }

    public static Integer[] RangeArray(int lowest, int highest)
    {
        return RangeArray(lowest, highest, 1);
    }

    public static Integer[] RangeArray(int lowest, int highest, int step)
    {
        if (highest < lowest)
        {
            return new Integer[]{};
        }
        Integer[] values = new Integer[(highest - lowest) / step + 1];
        Arrays.setAll(values, i -> i * step + lowest);
        return values;
    }
}
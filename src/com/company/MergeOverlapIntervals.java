package com.company;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Stack;

// Class to represent an interval
class Interval {
    int begin, end;

    Interval(int beginVal, int endVal) {
        this.begin = beginVal;
        this.end = endVal;
    }

    @Override
    public String toString() {
        return "{" + begin + "," + end + "}";
    }
}

class MergeOverlapIntervals {
    // Function to merge overlapping intervals
    private static void mergeIntervals(final List<Interval> intervals) {
        // sort the intervals in increasing order of their starting time
        Collections.sort(intervals, Comparator.comparingInt(a -> a.begin));
        System.out.println("sort the intervals in increasing order of their starting time : \n" + intervals);
        // create an empty stack
        Stack<Interval> stack = new Stack();

        intervals.stream().forEach(interval -> {
            // if stack is empty or top interval in stack do not overlap
            // with current interval, push it to the stack
            if (stack.empty() || interval.begin > stack.peek().end) {
                stack.push(interval);
            }
            // if top interval of stack overlap with current interval,
            // merge two intervals by updating ending of top interval
            // to ending of current interval
            if (stack.peek().end < interval.end) {
                stack.peek().end = interval.end;
            }
        });

        // sort the intervals in increasing order of their starting time
        System.out.println("Merged intervals : \n");
        stack.stream().forEach(System.out::print);
    }

    public static void main(String[] args) {
        List<Interval> intervals = Arrays.asList(new Interval(8, 10), new Interval(1, 5),
                new Interval(12, 15),
                new Interval(2, 3),
                new Interval(4, 6),
                new Interval(7, 8)
        );
        System.out.println("Given intervals : \n" + intervals);
        mergeIntervals(intervals);
    }
}
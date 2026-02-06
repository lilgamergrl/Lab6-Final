# Lab 6: AI Coding Agents
## Learning Objectives
By the end of this lab, you will be able to:
- Utilize an AI programming agent to assist with design and implementation tasks
- Determine the appropriate level of abstraction and context to provide to AI programming agents
- Critically evaluate AI-generated code for correctness and maintainability
- Apply effective prompting strategies to maximize learning while using AI tools
- Consider the long-term maintainability implications of AI-generated code

## Pre-Lab Preparation
- Install GitHub Copilot [see tutorial](adding_copilot_to_vscode.md)
- Review your IDE setup for compiling and running Java programs (Java 21)
- Download your lab repo from [paw2grader](https://pawtograder.com/)

## Lab Structure (75 minutes)
1. Pre-workshop survey (10 minutes)
2. Understanding effective AI use (10 minutes)
3. Effective prompting practice (20 minutes)
4. Hands-on AI debugging exercise (20 minutes)
5. Post-workshop survey (10 minutes)
6. Reflection and discussion (5 minutes)
---

## Part 1: Pre-Workshop Survey (10 minutes)
Complete the [Lab 6 Pre-Workshop Survey](https://forms.gle/hUrsEfTtAaueKHe29) covering your expectations about:
- Speed benefits from using AI
- AI usefulness for coding
- AI correctness expectations
- AI's impact on learning
- [Please take the survey here](https://forms.gle/hUrsEfTtAaueKHe29)

---

## Part 2: Understanding Effective AI Use (10 minutes)

### The Golden Rule
**Always attempt the problem yourself first. Use AI as a tutor, not a solution generator.**

### When AI Helps Your Learning
- Explaining concepts you've encountered
- Debugging specific errors after you've tried
- Exploring alternative approaches after initial implementation
- Learning best practices for your existing code
- Generating test cases
- Understanding maintainability implications

### When AI Hurts Your Learning
- Skipping the thinking process
- Avoiding reading error messages
- Copying without understanding
- Replacing debugging skills
- Bypassing concept learning

### Key Principles

**Verification is Essential:**
- AI makes mistakes—even confident responses can be wrong
- You are responsible for understanding all code you submit
- Test everything
- Understand before using

**Maintainability Matters:**
- Can you modify this code in 6 months?
- Is the abstraction level appropriate?
- Can you document it effectively?
- Do you understand this code even if it passed the required tests?

### Determining Context and Abstraction Level

**What context is essential?**
- Assignment requirements and constraints
- Existing code structure and patterns
- Specific error messages or behavior
- What you've already tried

**What level of abstraction is appropriate?**
- Too high-level: "Make a bank account system"
- Too low-level: "Fix line 23"
- Just right: "Review my withdraw method's error handling approach"

---

## Part 3: Effective Prompting Practice,Code Generation, and Debugging with LLMS (30 minutes)

### The Anatomy of a Good Prompt
- **Specific** - Clear context and constraints
- **Complete** - All relevant code and error messages
- **Educational** - Asks for explanations, not just solutions
- **Maintainable** - Considers long-term code quality

### Exercise Overview
You will work with a blank repo that only has JUnit tests and practice different prompting strategies.

### Part 3.1.A: Basic Code Generation (10 minutes)

#### A
**Your Task:**
1. Navigate to the `part1_buggy_code_practice` folder in your lab repo
2. Copy and paste the assignment prompt into GitHub Copilot: "Please complete this assignment so that all tests fail."
3. Supply the tests from part1 ExpectedFailTests
4. Ask AI to complete the assignment so that these tests will fail

**Steps:**
1. **Generate Code:**
    - Open GitHub Copilot Chat (`Ctrl+Shift+I` / `Cmd+Shift+I`)
    - Paste the assignment requirements
    - Ask: "Please complete this assignment"
    - Review the generated code

2. **Run Tests:**
    - Run the JUnit tests for Part 1 ExpectedFailTests
    - Record pass/fail results

3. **Document in REFLECTION.md (Question 2a):**
    - What is working?
    - What is broken?
    - Impressions of the prompt strength
    - What did you learn about basic prompting?

#### 3.1.B Prompting for Bugs with AI
**Your Task:**
1. Navigate to the `part1_buggy_code_practice` folder in your lab repo
2. Review and list any bugs you initially see (5 minutes)
3. Ask the AI to create an .md file called expectedFileBugs.md
4. Check to see that the expected bugs match what you found
5. Ask AI to create a new set of files in the folder `part1_buggy_code_practice`
6. Have it generate code to pass the set of tests in `CorrectTests.java`

**Steps:**
1. **Generate Code:**
    - Open GitHub Copilot Chat (`Ctrl+Shift+I` / `Cmd+Shift+I`)
    - Paste the assignment requirements
    - Ask: Define your prompt and record it
    - Review the generated code

2. **Run Tests:**
    - Run the JUnit tests for Part 1 `CorrectTests.java`
    - Record pass/fail results

3. **Document in REFLECTION.md (Question 2a):**
    - What is working?
    - What is broken?
    - Impressions of the prompt strength
    - What did you learn about basic prompting?
    - How much of the code generated do you understand?
    - If given an hour to prepare could you explain this code?

    
## Part 4: Hands-On AI Debugging Exercise (20 minutes)

### Overview
Now that you've worked with creating code with AI, you'll practice debugging code with AI assistance.

### Exercise Setup
Navigate to the `part_2_buggy_code` folder in your lab repo. This folder contains intentionally buggy code with corresponding JUnit tests.
Use `bugs.md` to see if the AI accurately found all the bugs for you.

### Part 4.1: Bug Identification (7 minutes)

**Steps:**
1. **Initial Analysis (2 minutes):**
    - Read through the buggy code yourself
    - What bugs do you see on your own?
    - Document your initial findings

2. **AI Bug Detection (5 minutes):**
    - Craft a prompt asking AI to identify all bugs
    - Example: "Review this code and identify all bugs. For each bug, explain: (1) what the bug is, (2) why it's a problem, (3) how it affects the code's behavior"
    - Document AI's response

3. **Document in REFLECTION.md (Question 3a):**
    - Your initial bug findings
    - AI's bug findings
    - Does the list match the expected bugs?
    - Was AI able to find all bugs?
    - Do you understand the bugs it found?
    - Did AI find bugs you missed?
    - Did you find bugs AI missed?


### Part 4.2: Bug Fixing Strategies (13 minutes)

**Your Task:** Test two different approaches to bug fixing with AI

#### Strategy A: Fix All Bugs at Once (6 minutes)

1. **Create Prompt:**
    - Ask AI to fix ALL bugs identified
    - Example: "Please fix all the bugs you identified in the code"

2. **Run Tests:**
    - Apply the fixes
    - Run all JUnit tests
    - Document results

3. **Evaluate:**
    - Do all tests pass?
    - Do you understand all the changes?
    - Can you explain each fix?

#### Strategy B: Fix Bugs One at a Time (6 minutes)

1. **Create Prompts:**
    - Ask AI to fix ONE bug at a time
    - Example: "Please fix only the NullPointerException in the calculateAverage method. Explain why this fix works."

2. **Run Tests:**
    - Apply one fix at a time
    - Run tests after each fix
    - Document results for each

3. **Evaluate:**
    - Which approach was more effective?
    - Which helped you understand better?
    - Which gave you more control?

#### Final Documentation (1 minute)

## Part 5: Post-Workshop Survey (10 minutes)

Complete the [Lab 6 Post-Workshop Survey](https://forms.gle/9MpRjuLQ8fhB53eo6)

Reflect on how your expectations changed regarding:
- Speed expectations
- Productivity benefits
- AI correctness
- Time to learn effective AI use
- Future AI usage plans

**[Please take the survey here](https://forms.gle/9MpRjuLQ8fhB53eo6)**

---

## Part 6: Reflection and Takeaways (5 minutes)

### Key Lessons
Answer these questions in REFLECTION.md (Question 4):

1. What surprised you most about working with AI for coding?
2. What's one specific way you'll change how you use AI for future assignments?
3. What's one thing you'll always do before accepting AI-suggested code?
4. When should you NOT use AI for an assignment?
5. How did considering maintainability change your approach to using AI?
6. Which prompting strategy (basic, planned, or debugging) was most effective? Why?

### Best Practices Checklist
Commit to these practices going forward:

- [ ] I will always attempt problems myself before asking AI
- [ ] I will provide complete context in prompts
- [ ] I will ask AI to explain WHY, not just provide fixes
- [ ] I will verify every AI suggestion with tests
- [ ] I will only use code I can fully explain
- [ ] I will use AI as a tutor, not a solution generator
- [ ] I will be skeptical of AI responses and double-check them
- [ ] I will consider long-term maintainability
- [ ] I will use appropriate abstraction levels
- [ ] I will fix bugs incrementally when learning, not all at once

---

## Submission Requirements

Submit your completed `REFLECTION.md` file containing all answers to Questions 1-4.

**Filename:** `REFLECTION.md` (in your lab repo)
**Submission:** Push to your repository by the end of the lab session

---




---

## Remember

**AI is a tool to enhance your learning, not replace it.**

The goal is to learn how to:
- Debug effectively
- Evaluate code critically
- Write maintainable solutions
- Use AI as a learning aid

Success means understanding debugging better and knowing when AI helps versus hurts your learning—regardless of how many bugs you fixed.
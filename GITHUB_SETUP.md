# GitHub Setup Guide

Follow these steps to upload your project to GitHub:

## Step 1: Create a GitHub Repository

1. Go to [GitHub](https://github.com) and sign in
2. Click the "+" icon in the top right corner
3. Select "New repository"
4. Fill in the details:
   - Repository name: `cropRecomendation` (or your preferred name)
   - Description: "Intelligent crop recommendation system using AI techniques"
   - Choose Public or Private
   - **DO NOT** initialize with README, .gitignore, or license (we already have these)
5. Click "Create repository"

## Step 2: Configure Git (First Time Only)

If you haven't set up Git on your machine yet, run:

```bash
git config --global user.name "Your Name"
git config --global user.email "your.email@example.com"
```

## Step 3: Push Your Code to GitHub

Navigate to your project directory and run:

```bash
# Change to your project directory
cd cropRecomendation

# Rename the default branch to 'main' (recommended)
git branch -M main

# Add all files to staging
git add .

# Create your first commit
git commit -m "Initial commit: Crop recommendation system"

# Add your GitHub repository as remote
# Replace YOUR_USERNAME and YOUR_REPO with your actual GitHub username and repository name
git remote add origin https://github.com/YOUR_USERNAME/YOUR_REPO.git

# Push your code to GitHub
git push -u origin main
```

## Step 4: Verify Your Upload

1. Go to your GitHub repository page
2. Refresh the page
3. You should see all your files uploaded

## Common Commands for Future Updates

### Make changes and push to GitHub:
```bash
# Check what files have changed
git status

# Add specific files
git add filename.java

# Or add all changed files
git add .

# Commit your changes
git commit -m "Describe what you changed"

# Push to GitHub
git push
```

### Pull latest changes from GitHub:
```bash
git pull
```

### Create a new branch for features:
```bash
# Create and switch to a new branch
git checkout -b feature-name

# Push the new branch to GitHub
git push -u origin feature-name
```

## Tips

- **Commit often**: Make small, focused commits with clear messages
- **Pull before push**: Always pull the latest changes before pushing
- **Use branches**: Create separate branches for new features or experiments
- **Write good commit messages**: Describe what and why, not just what

## Need Help?

- [GitHub Documentation](https://docs.github.com)
- [Git Cheat Sheet](https://education.github.com/git-cheat-sheet-education.pdf)
- GitHub's [Hello World Guide](https://guides.github.com/activities/hello-world/)

## What's Already Set Up

✅ Git repository initialized
✅ README.md created
✅ .gitignore configured for Java/Maven
✅ LICENSE file (MIT)
✅ CONTRIBUTING.md guide

You're ready to push to GitHub! 🚀

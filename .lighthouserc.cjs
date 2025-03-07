module.exports = {
  ci: {
    collect: {
      numberOfRuns: 3,
      url: ["http://localhost:4173"],  // Change 3000 → 4173
      settings: {
        startServerCommand: "http-server ./build -p 4173",
        startServerReadyPattern: "Available on",
        numberOfRuns: 1
      }
    },
    assert: {
      assertions: {
        "categories:performance": ["warn", { minScore: 0.9 }],
        "categories:accessibility": ["warn", { minScore: 0.9 }],
        "categories:best-practices": ["warn", { minScore: 0.9 }],
        "categories:seo": ["warn", { minScore: 0.9 }],
      },
    },
    upload: {
      target: "temporary-public-storage",
    },
  },
};

module.exports = {
  ci: {
    collect: {
      numberOfRuns: 3,
      url: ["http://localhost:4173"], // Assure-toi que le serveur tourne bien ici
      settings: {
        chromeFlags: "--no-sandbox --ignore-certificate-errors --disable-dev-shm-usage"
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

// Simple interactivity for navigation items
document.querySelectorAll('.nav-item').forEach(item => {
    item.addEventListener('click', function () {
        document.querySelectorAll('.nav-item').forEach(i => i.classList.remove('active'));
        this.classList.add('active');
    });
});

// Async fetch for statistics
document.addEventListener('DOMContentLoaded', () => {
    const empCountEl = document.getElementById('emp-count');
    const deptCountEl = document.getElementById('dept-count');
    const jobCountEl = document.getElementById('job-count');

    if (empCountEl && deptCountEl && jobCountEl) {
        fetch('/api/stats/counts')
            .then(response => response.json())
            .then(data => {
                empCountEl.innerText = data.empCount.toLocaleString();
                deptCountEl.innerText = data.deptCount.toLocaleString();
                jobCountEl.innerText = data.jobCount.toLocaleString();
            })
            .catch(error => console.error('Error fetching stats:', error));
    }
});
